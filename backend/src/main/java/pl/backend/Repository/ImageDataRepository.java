package pl.backend.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.backend.Model.ImageData;

public interface ImageDataRepository extends JpaRepository<ImageData, Long> {
    Optional<ImageData> findByName(String name);
}
