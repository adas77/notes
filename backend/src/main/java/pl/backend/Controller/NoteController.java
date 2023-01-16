package pl.backend.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.backend.Config.JwtService;
import pl.backend.Dto.NoteDto;
import pl.backend.Model.Note;
import pl.backend.Service.HashNotesService;
import pl.backend.Service.NoteService;
import pl.backend.utils.PasswordStrength;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final JwtService jwtService;

    @GetMapping("/{noteId}")
    public Note getNoteById(@PathVariable Long noteId) {
        return noteService.getById(noteId);
    }

    @GetMapping("/public")
    public Set<NoteDto> getPublicNotes() {
        return noteService.getPublic();
    }

    @GetMapping("/protected/{noteId}")
    public String getProtectedNote(@PathVariable Long noteId,
            @RequestParam(value = "password", required = true) String password,
            @RequestHeader(name = "Authorization") String token)
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException,
            InvalidKeySpecException {
        String username = jwtService.extractUsername(token);
        if (PasswordStrength.validateCommonPass(password) && PasswordStrength.validatePassStrength(password)) {
            return noteService.getProtected(noteId, password, username);
        }
        throw new IllegalAccessError();
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public void createNote(@RequestBody Note note, @RequestParam(name = "password", required = false) String password,
            @RequestHeader(name = "Authorization") String token)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {

        String username = jwtService.extractUsername(token);
        noteService.create(username, note, password);
    }

    @GetMapping
    public Set<NoteDto> getUserNotes(@RequestHeader(name = "Authorization") String token)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {

        String username = jwtService.extractUsername(token);
        return noteService.getUserNotes(username);
    }
}
