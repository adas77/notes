package pl.backend.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;
import pl.backend.Dto.ImageUploadResponse;
import pl.backend.Model.ImageData;
import pl.backend.Repository.ImageDataRepository;
import pl.backend.utils.ImageUtil;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageDataService {

    @Autowired
    private ImageDataRepository imageDataRepository;

    public ImageUploadResponse uploadImage(MultipartFile file) throws IOException {

        imageDataRepository.save(ImageData.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes())).build());

        return new ImageUploadResponse("Image uploaded successfully: " +
                file.getOriginalFilename());
    }

    @Transactional
    public byte[] getImage(String name) {
        Optional<ImageData> dbImage = imageDataRepository.findByName(name);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }

}