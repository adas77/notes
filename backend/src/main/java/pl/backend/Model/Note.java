package pl.backend.Model;

import jakarta.persistence.*;
import lombok.*;


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
}