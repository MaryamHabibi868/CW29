package ir.maktab.cw29.repository;

import ir.maktab.cw29.domain.User;
import ir.maktab.cw29.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {

Optional<User> findByUsername(String username);



}
