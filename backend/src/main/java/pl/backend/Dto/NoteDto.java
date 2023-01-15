package pl.backend.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.backend.Model.NoteStatus;

@AllArgsConstructor
@Getter
@Setter
public class NoteDto {
    private Long id;
    private String username;
    private String note;
    private NoteStatus noteStatus;
    private LocalDateTime date;
}
