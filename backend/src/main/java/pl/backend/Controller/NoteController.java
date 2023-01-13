package pl.backend.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Optional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import pl.backend.Model.Note;
import pl.backend.Service.NoteService;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @GetMapping("/{noteId}")
    public Note getNoteById(@PathVariable Long noteId) {
        return noteService.getById(noteId);
    }

    @GetMapping("/protected/{noteId}")
    public String getProtectedNote(@PathVariable Long noteId,
            @RequestParam(value = "password") String password)
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException,
            InvalidKeySpecException {
        return noteService.getProtected(noteId, password);
    }

    @PostMapping()
    public Note createNote(@RequestBody Note note, @RequestParam(name = "password", required = false) String password)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        return noteService.create(note, Optional.of(password));
    }
}
