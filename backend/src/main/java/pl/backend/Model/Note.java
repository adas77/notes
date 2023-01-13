package pl.backend.Model;

import lombok.*;

import javax.persistence.*;

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