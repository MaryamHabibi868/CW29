package ir.maktab.cw29.service;

import ir.maktab.cw29.domain.Post;
import ir.maktab.cw29.dto.PostResponse;
import ir.maktab.cw29.dto.PostSaveDTO;
import ir.maktab.cw29.dto.PostUpdateDTO;
import ir.maktab.cw29.exception.ExceptionNotFoundData;
import ir.maktab.cw29.mapper.PostMapper;
import ir.maktab.cw29.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public PostServiceImpl(PostRepository postRepository,
                           PostMapper postMapper) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
    }


    @Override
    public PostResponse save(PostSaveDTO request) {
        return postMapper.entityMapToResponse(
                postRepository.save(postMapper.saveMapToEntity(request)));
    }

    @Override
    public PostResponse update(PostUpdateDTO request) {
        return postMapper.entityMapToResponse(
                postRepository.save(postMapper.updateMapToEntity(request)));
    }


    @Override
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public PostResponse findById(Long id) {
        Post byId = postRepository.findById(id).orElseThrow(
                () -> new ExceptionNotFoundData("Post not found")
        );
        return postMapper.entityMapToResponse(byId);
    }


    @Override
    public Page<PostResponse> findAll(Integer pageSize, Integer pageNumber) {
        Page<Post> all = postRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return all.map(postMapper::entityMapToResponse);
    }

    @Override
    public Page<PostResponse> findAllByAuthorId(Long authorId, Integer pageSize,
                                                Integer pageNumber) {
        Page<Post> all = postRepository.findAll(PageRequest.of(pageNumber, pageSize));
        return all.map(postMapper::entityMapToResponse);
    }
}
