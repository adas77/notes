package pl.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pl.backend.Dto.ImageUploadResponse;
import pl.backend.Model.ImageData;
import pl.backend.Model.User;
import pl.backend.Repository.ImageDataRepository;
import pl.backend.Repository.UserRepository;
import pl.backend.utils.ImageUtil;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ImageDataService {

    // @Autowired
    private final ImageDataRepository imageDataRepository;
    private final UserRepository userRepository;

    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {

        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());

    }

    @Transactional
    public ImageData getInfoByImageByName(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);

        return ImageData.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .imageData(ImageUtil.decompressImage(dbImage.get().getImageData())).build();

    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }

    @Transactional
    public ImageUploadResponse createForUser(String username, MultipartFile file) throws IOException {
        User user = userRepository.findByUsername(username).orElseThrow(RuntimeException::new);
        ImageData imageData = ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build();

        user.addToImage(imageData);
        imageDataRepository.save(imageData);
        userRepository.save(user);

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());
    }

    public Set<byte[]> getUserImages(String username) {

        Set<byte[]> images = imageDataRepository.findAll().stream()
                .filter(i -> username.equals(i.getUser().getUsername()))
                .map(i -> ImageUtil.decompressImage(i.getImageData())).collect(Collectors.toSet());
        return images;
    }

}