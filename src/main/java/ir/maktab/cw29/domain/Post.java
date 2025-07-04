package ir.maktab.cw29.domain;

import ir.maktab.cw29.domain.base.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Getter
@Setter
@ToString
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Post extends BaseEntity {

    private String title;
    private String content;

    @ManyToOne
    private Author author;
}
