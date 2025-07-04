package ir.maktab.cw29.controller;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.LoginAuthorDTO;
import ir.maktab.cw29.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("api/author")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping("/login")
    public ResponseEntity<AuthorResponseDTO> loginAuthor(@RequestBody LoginAuthorDTO loginAuthorDTO) {
        return ResponseEntity.ok(authorService.loginAuthor(loginAuthorDTO));
    }
}
