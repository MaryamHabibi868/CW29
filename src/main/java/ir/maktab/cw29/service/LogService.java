package ir.maktab.cw29.service;

import ir.maktab.cw29.document.Log;
import ir.maktab.cw29.dto.LogDto;
import ir.maktab.cw29.dto.RequestsPerUserDto;
import ir.maktab.cw29.dto.UserRequestPerStatusDto;
import org.bson.Document;

import java.util.List;

public interface LogService {

    void create(LogDto dto);

    List<RequestsPerUserDto> getCountByUser();

    List<UserRequestPerStatusDto> getByUserId(Long userId);
}
