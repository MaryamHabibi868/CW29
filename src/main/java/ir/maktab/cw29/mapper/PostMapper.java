package ir.maktab.cw29.mapper;

import ir.maktab.cw29.domain.Post;
import ir.maktab.cw29.dto.PostResponse;
import ir.maktab.cw29.dto.PostSaveDTO;
import ir.maktab.cw29.dto.PostUpdateDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostMapper {

    Post updateMapToEntity(PostUpdateDTO postUpdateDTO);

    PostResponse entityMapToResponse(Post post);

    PostResponse mapToResponse(PostSaveDTO postSaveDTO);

    Post saveMapToEntity(PostSaveDTO postSaveDTO);





}
