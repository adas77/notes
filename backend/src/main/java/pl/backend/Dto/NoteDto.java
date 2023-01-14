package pl.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.backend.Model.NoteStatus;

@AllArgsConstructor
@Getter
@Setter
public class NoteDto {
    private String username;
    private String note;
    private NoteStatus noteStatus;
}
