package ir.maktab.cw29.repository;

import ir.maktab.cw29.dto.RequestsPerUserDto;
import ir.maktab.cw29.dto.UserRequestPerStatusDto;
import org.bson.Document;

import java.util.List;

public interface LogRepositoryCustom {
    List<RequestsPerUserDto> findRequestNumbersPerUser();

    List<UserRequestPerStatusDto> findRequestsByUser(Long userId);
}
