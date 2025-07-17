package ir.maktab.cw29.controller;

import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.LoginAuthorDTO;
import ir.maktab.cw29.dto.RegisterUpdateAuthor;
import ir.maktab.cw29.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/author")
public class AuthorController {

    private final AuthorService authorService;


    @PostMapping("/register")
    public ResponseEntity<AuthorResponseDTO> registerAuthor(
            @RequestBody RegisterUpdateAuthor registerUpdateAuthor) {
        return ResponseEntity.ok(authorService.registrationAuthor(registerUpdateAuthor));
    }
}
