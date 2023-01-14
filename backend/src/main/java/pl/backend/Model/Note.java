package pl.backend.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


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
    private String note;
    @Enumerated(EnumType.STRING)
    private NoteStatus noteStatus;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public Note(String note, NoteStatus noteStatus){
        this.note=note;
        this.noteStatus=noteStatus;
    }
}