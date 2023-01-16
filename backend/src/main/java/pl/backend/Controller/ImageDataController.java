package pl.backend.Controller;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.backend.Config.JwtService;
import pl.backend.Dto.ImageUploadResponse;
import pl.backend.Service.ImageDataService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageDataController {

    // @Autowired
    private final ImageDataService imageDataService;
    private final JwtService jwtService;

    // @PostMapping
    // public ResponseEntity<?> uploadImage(@RequestParam("image") MultipartFile
    // file) throws IOException {
    // ImageUploadResponse response = imageDataService.uploadImage(file);

    // return ResponseEntity.status(HttpStatus.OK)
    // .body(response);
    // }

    // @GetMapping("/info/{name}")
    // public ResponseEntity<?> getImageInfoByName(@PathVariable("name") String
    // name) {
    // ImageData image = imageDataService.getInfoByImageByName(name);

    // return ResponseEntity.status(HttpStatus.OK)
    // .body(image);
    // }

    // @GetMapping("/{name}")
    // public ResponseEntity<?> getImageByName(@PathVariable("name") String name) {
    // byte[] image = imageDataService.getImage(name);

    // return ResponseEntity.status(HttpStatus.OK)
    // .contentType(MediaType.valueOf("image/png"))
    // .body(image);
    // }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<ImageUploadResponse> uploadImage(@RequestParam("image") MultipartFile file,
            @RequestHeader(name = "Authorization") String token) throws IOException {

        String username = jwtService.extractUsername(token);
        ImageUploadResponse response = imageDataService.createForUser(username, file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }

    @GetMapping
    public Set<ResponseEntity<?>> getUserImages(@RequestHeader(name = "Authorization") String token) {

        String username = jwtService.extractUsername(token);

        Set<ResponseEntity<?>> images = imageDataService.getUserImages(username).stream()
                .map(i -> ResponseEntity.status(HttpStatus.OK)
                        .contentType(MediaType.valueOf("image/png"))
                        .body(i))
                .collect(Collectors.toSet());

        return images;

    }

}