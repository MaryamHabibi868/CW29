package ir.maktab.cw29.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSaveDTO {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Long authorId;
}
