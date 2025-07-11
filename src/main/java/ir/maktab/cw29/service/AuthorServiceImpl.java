package ir.maktab.cw29.service;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.LoginAuthorDTO;
import ir.maktab.cw29.dto.RegisterUpdateAuthor;
import ir.maktab.cw29.exception.ExceptionNotFoundData;
import ir.maktab.cw29.mapper.AuthorMapper;
import ir.maktab.cw29.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;


    @Override
    public AuthorResponseDTO loginAuthor(LoginAuthorDTO loginAuthorDTO) {
       return  authorMapper.mapToDTO(authorRepository
               .findByUsernameAndPassword(loginAuthorDTO.getUsername(),
                loginAuthorDTO.getPassword())
               .orElseThrow(()-> new ExceptionNotFoundData("Author not found")));
    }

    @Transactional
    @Override
    public AuthorResponseDTO registrationAuthor(RegisterUpdateAuthor registerUpdateAuthor) {
        return authorMapper.mapToDTO(authorRepository
                .save(authorMapper.mapTpEntity(registerUpdateAuthor)));
    }

    @Override
    public Author findByUsername(String username) {
        return authorRepository.findByUsername(username).
                orElseThrow(()->
                        new ExceptionNotFoundData("Author not found"));
    }
}
