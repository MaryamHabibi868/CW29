package ir.maktab.cw29.domain;

import ir.maktab.cw29.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Author extends BaseEntity {

    private String firstName;
    private String lastName;
    private String username;
    private String password;

    @OneToMany (mappedBy = "author")
    private Set<Post> posts;
}
