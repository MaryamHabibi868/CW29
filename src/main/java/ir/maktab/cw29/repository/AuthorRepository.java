package ir.maktab.cw29.repository;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends BaseRepository<Author> {

    Optional<Author> findByUsernameAndPassword(String username, String password);

    Optional<Author> findByUsername(String username);
}
