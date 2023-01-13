package pl.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.backend.Model.DeviceMetadata;

public interface DeviceMetadataRepository extends JpaRepository<DeviceMetadata, Long> {
    List<DeviceMetadata> findByUserId(Long userId);

}
