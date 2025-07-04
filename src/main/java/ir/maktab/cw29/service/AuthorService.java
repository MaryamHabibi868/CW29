package ir.maktab.cw29.service;


import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.LoginAuthorDTO;
import ir.maktab.cw29.dto.RegisterUpdateAuthor;

public interface AuthorService {

     AuthorResponseDTO loginAuthor(LoginAuthorDTO loginAuthorDTO);

     AuthorResponseDTO registrationAuthor(RegisterUpdateAuthor registerUpdateAuthor);
}
