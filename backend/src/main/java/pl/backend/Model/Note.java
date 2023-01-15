package pl.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Size;

@Entity
@Table(name = "note")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @Column(columnDefinition = "text")
    @Column(length = 2048)
    private String note;
    @Enumerated(EnumType.STRING)
    private NoteStatus noteStatus;
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Note(String note, NoteStatus noteStatus) {
        this.note = note;
        this.noteStatus = noteStatus;
        this.dateTime = LocalDateTime.now();
    }
}