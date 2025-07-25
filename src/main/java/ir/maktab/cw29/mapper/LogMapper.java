package ir.maktab.cw29.mapper;

import ir.maktab.cw29.document.Log;
import ir.maktab.cw29.dto.LogDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LogMapper {

    Log mapToEntity(LogDto log);
}
