package ir.maktab.cw29.controller;

import ir.maktab.cw29.dto.PostResponse;
import ir.maktab.cw29.dto.PostSaveDTO;
import ir.maktab.cw29.dto.PostUpdateDTO;
import ir.maktab.cw29.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/save")
    public ResponseEntity<PostResponse> save(@RequestBody PostSaveDTO request) {
        Object principal = SecurityContextHolder.
                getContext().getAuthentication().getPrincipal();

        return ResponseEntity.ok(postService.save(request));
    }

    @PutMapping("/update")
    public ResponseEntity<PostResponse> update(@RequestBody PostUpdateDTO request) {
        return ResponseEntity.ok(postService.update(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(
            @PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok("Post Deleted");
    }

    @GetMapping("/find/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_AUTHOR')")
    public ResponseEntity<PostResponse> findById(@PathVariable Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/find-all")
    @PreAuthorize("hasAnyAuthority('ROLE_USER','ROLE_AUTHOR')")
    public ResponseEntity<Page<PostResponse>> findAll(
            @RequestParam(defaultValue = "10")Integer pageSize
            , @RequestParam(defaultValue = "0") Integer pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        return ResponseEntity.ok(postService.findAll(pageable));
    }

    @GetMapping("/find-all-by-author-id/{author-id}/{page-size}/{page-number}")
    @PreAuthorize("hasAuthority('ROLE_USER'or'ROLE_AUTHOR')")
    public ResponseEntity<Page<PostResponse>> findAllByAuthorId(
            @PathVariable(name = "author-id") Long authorId,
            @PathVariable(name = "page-size") Integer pageSize,
            @PathVariable(name = "page-number") Integer pageNumber) {
        return ResponseEntity.ok(
                postService.findAllByAuthorId(authorId, pageSize, pageNumber));
    }

}
