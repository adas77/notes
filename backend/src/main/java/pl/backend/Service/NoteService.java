package pl.backend.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.backend.Dto.NoteDto;
import pl.backend.Model.Note;
import pl.backend.Model.NoteStatus;
import pl.backend.Model.User;
import pl.backend.Repository.NoteRepository;
import pl.backend.Repository.UserRepository;

@Service
@AllArgsConstructor
@Slf4j
// @AllArgsConstructor
public class NoteService {
    private final HashNotesService hashNotesService;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public void create(String username, Note note, String password) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        Note newNote = new Note(note.getNote(), note.getNoteStatus());

        if (note.getNoteStatus() == NoteStatus.PRIVATE_ENCODED) {
            String encrypt = hashNotesService.encrypt(note.getNote(), hashNotesService.getKeyFromPassword(password));
            log.info(encrypt);
            newNote.setNote(encrypt);
        }
        user.addToNotes(newNote);
        noteRepository.save(newNote);
        userRepository.save(user);
    }

    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "xd not found"));
    }

    public String getProtected(Long noteId, String password, String username)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        Note protectedNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ops"));

        if (!username.equals(protectedNote.getUser().getUsername())) {
            // TODO: Honeypot?
            return "Nie wolno";
        }
        String decrypted = hashNotesService.decrypt(protectedNote.getNote(),
                hashNotesService.getKeyFromPassword(password));

        return decrypted;
    }

    public Set<NoteDto> getPublic() {
        return noteRepository.findAll().stream().filter(n -> NoteStatus.PUBLIC.equals(n.getNoteStatus()))
                .map(n -> new NoteDto(n.getId(), n.getUser().getUsername(), n.getNote(), n.getNoteStatus()))
                .collect(Collectors.toSet());
    }

    public Set<NoteDto> getUserNotes(String username) {
        return noteRepository.findAll().stream().filter(n -> username.equals(n.getUser().getUsername()))
                .map(n -> new NoteDto(n.getId(), n.getUser().getUsername(), n.getNote(), n.getNoteStatus()))
                .collect(Collectors.toSet());
    }

    // public Note update(Long id, Note newNote) {
    // if (!Objects.equals(id, newNote.getId())) {
    // throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ops");
    // }
    // return noteRepository.save(newNote);
    // }

    // public void delete(Long id) {
    // noteRepository.deleteById(id);
    // }

}
