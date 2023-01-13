package pl.backend.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.backend.Model.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    // Optional<User> findByUsername(String username);

    // Boolean existsByUsername(String username);

    // Boolean existsByEmail(String email);

}
