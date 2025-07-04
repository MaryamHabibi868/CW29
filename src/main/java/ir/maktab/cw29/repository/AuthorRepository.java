package ir.maktab.cw29.repository;

import ir.maktab.cw29.domain.Author;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends BaseRepository <Author> {

    Optional<Author> findByUsernameAndPassword(String username, String password);
}
