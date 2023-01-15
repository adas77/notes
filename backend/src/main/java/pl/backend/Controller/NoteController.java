package pl.backend.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pl.backend.Config.JwtService;
import pl.backend.Dto.NoteDto;
import pl.backend.Model.Note;
import pl.backend.Model.NoteStatus;
import pl.backend.Service.NoteService;

@Slf4j
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
        // return List.of(new Note("null", NoteStatus.PUBLIC));
    }

    @PostMapping("/add")
    public Note add() {
        return new Note("ddd", NoteStatus.PRIVATE_ENCODED);
    }

    @GetMapping("/protected/{noteId}")
    public String getProtectedNote(@PathVariable Long noteId,
            @RequestParam(value = "password", required = true) String password,
            @RequestHeader(name = "Authorization") String token)
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException,
            InvalidKeySpecException {
        String username = jwtService.extractUsername(token);
        return noteService.getProtected(noteId, password, username);
    }

    // @RequestMapping(value = "/users", method = RequestMethod.GET)
    // public Set<Note> getAll(@RequestHeader(name = "Authorization") String token)
    // {
    // String username = jwtService.extractUsername(token);
    // return noteService.getUserNotes(username);
    // }

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

    // @RequestMapping( method = RequestMethod.POST)
    // public Note createNote(@RequestBody Note note, @RequestParam(name =
    // "password", required = false) String password,@RequestHeader
    // (name="Authorization") String token)
    // throws InvalidKeyException, NoSuchPaddingException,
    // NoSuchAlgorithmException, BadPaddingException,
    // IllegalBlockSizeException, InvalidKeySpecException {
    // String username = jwtService.extractUsername(token);
    //
    // return noteService.create(username,note, Optional.of(password));
    // }
    // @PostMapping()
    // public Note createNote(@RequestBody Note note, @RequestParam(name =
    // "password", required = false) String password)
    // throws InvalidKeyException, NoSuchPaddingException,
    // NoSuchAlgorithmException, InvalidAlgorithmParameterException,
    // BadPaddingException,
    // IllegalBlockSizeException, InvalidKeySpecException {
    // return noteService.create(note, Optional.of(password));
    // }
}
