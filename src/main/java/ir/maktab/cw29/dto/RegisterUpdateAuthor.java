package ir.maktab.cw29.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterUpdateAuthor {

    private String firstName;
    private String lastName;


    private String username;
    private String password;
}
