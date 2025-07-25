package ir.maktab.cw29.service;

import ir.maktab.cw29.dto.PostResponse;
import ir.maktab.cw29.dto.PostSaveDTO;
import ir.maktab.cw29.dto.PostUpdateDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PostService {

    PostResponse save(PostSaveDTO request);

    PostResponse update(PostUpdateDTO request);

    void deleteById(Long id);

    PostResponse findById(Long id);

    Page<PostResponse> findAll(Pageable pageable);

    Page<PostResponse> findAllByAuthorId(Long authorId, Integer page, Integer size);
}
