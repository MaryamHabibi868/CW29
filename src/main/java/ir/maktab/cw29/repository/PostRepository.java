package ir.maktab.cw29.repository;

import ir.maktab.cw29.domain.Post;
import ir.maktab.cw29.repository.base.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends BaseRepository<Post> {

    Page<Post> findAllByAuthor_Id(Long id,
                                  Pageable pageable);

}
