package pl.backend.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.backend.Model.Note;
import pl.backend.Model.NoteStatus;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
    List<Note> findByNoteStatus(NoteStatus status);
}
