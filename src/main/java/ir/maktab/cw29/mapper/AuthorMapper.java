package ir.maktab.cw29.mapper;

import ir.maktab.cw29.domain.Author;
import ir.maktab.cw29.dto.AuthorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;


@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthorMapper {

    //Map for Login
    AuthorResponseDTO mapToDTO(Author author);
}
