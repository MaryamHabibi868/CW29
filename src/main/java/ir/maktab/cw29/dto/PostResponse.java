package ir.maktab.cw29.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResponse {


    private Long id;


    private String title;


    private String content;


    private Long authorId;
}
