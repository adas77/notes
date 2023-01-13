package pl.backend.Service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Objects;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.backend.Model.Note;
import pl.backend.Repository.NoteRepository;

@Service
@AllArgsConstructor
// @Slf4j
// @AllArgsConstructor
public class NoteService {
    private final HashNotesService hashNotesService;
    private final NoteRepository noteRepository;

    public Note create(Note note, Optional<String> password) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        switch (note.getNoteStatus()) {
            case PRIVATE_ENCODED:
                note.setNote(
                        hashNotesService.encrypt(note.getNote(), hashNotesService.getKeyFromPassword(password.get())));

                break;
            case PRIVATE:
                return noteRepository.save(note);

            case PUBLIC:
                return noteRepository.save(note);
            default:
                break;
        }
        return noteRepository.save(note);
    }

    public Note getById(Long id) {
        return noteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "xd not found"));
    }

    public String getProtected(Long noteId, String password) throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        Note protectedNote = noteRepository.findById(noteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "ops"));
        String decrypted = hashNotesService.decrypt(protectedNote.getNote(),
                hashNotesService.getKeyFromPassword(password));

        return decrypted;
    }

    public Note update(Long id, Note newNote) {
        if (!Objects.equals(id, newNote.getId())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ops");
        }
        return noteRepository.save(newNote);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }

}
