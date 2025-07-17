package ir.maktab.cw29.mapper;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import ir.maktab.cw29.dto.RegisterUpdateAuthor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;
import org.springframework.security.crypto.password.PasswordEncoder;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {



    //Map for Login
    AuthorResponseDTO mapToDTO(Author author);


    @Mapping(target = "password" , ignore = true )
    Author mapTpEntity(RegisterUpdateAuthor author);

}
