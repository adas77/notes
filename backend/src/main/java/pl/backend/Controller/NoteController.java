package pl.backend.Controller;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import pl.backend.Config.JwtService;
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
    public List<Note> getPublicNotes() {
        return noteService.getPublic();
    }

    @PostMapping("/add")
    public Note add(){
        return new Note("ddd", NoteStatus.PRIVATE_ENCODED);
    }

    @GetMapping("/protected/{noteId}")
    public String getProtectedNote(@PathVariable Long noteId,
            @RequestParam(value = "password") String password)
            throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException,
            InvalidKeySpecException {
        return noteService.getProtected(noteId, password);
    }


    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public Set<Note> getAll(@RequestHeader (name="Authorization") String token){
        String username = jwtService.extractUsername(token);
        return noteService.getNotes(username);
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public Note createNote(@RequestBody Note note, @RequestParam(name = "password", required = false) String password,@RequestHeader (name="Authorization") String token)
            throws InvalidKeyException, NoSuchPaddingException,
            NoSuchAlgorithmException, BadPaddingException,
            IllegalBlockSizeException, InvalidKeySpecException {
        log.info("username");
        log.info(token);
        log.error(token);
        log.debug(token);

        String username = jwtService.extractUsername(token);
        log.info(username);

        return noteService.create(username,note, password);
    }

//    @RequestMapping( method = RequestMethod.POST)
//    public Note createNote(@RequestBody Note note, @RequestParam(name = "password", required = false) String password,@RequestHeader (name="Authorization") String token)
//            throws InvalidKeyException, NoSuchPaddingException,
//            NoSuchAlgorithmException, BadPaddingException,
//            IllegalBlockSizeException, InvalidKeySpecException {
//        String username = jwtService.extractUsername(token);
//
//        return noteService.create(username,note, Optional.of(password));
//    }
//    @PostMapping()
//    public Note createNote(@RequestBody Note note, @RequestParam(name = "password", required = false) String password)
//            throws InvalidKeyException, NoSuchPaddingException,
//            NoSuchAlgorithmException, InvalidAlgorithmParameterException, BadPaddingException,
//            IllegalBlockSizeException, InvalidKeySpecException {
//        return noteService.create(note, Optional.of(password));
//    }
}
