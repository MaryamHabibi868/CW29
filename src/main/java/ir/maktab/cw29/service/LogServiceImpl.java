package ir.maktab.cw29.service;

import ir.maktab.cw29.document.Log;
import ir.maktab.cw29.dto.LogDto;
import ir.maktab.cw29.dto.RequestsPerUserDto;
import ir.maktab.cw29.dto.UserRequestPerStatusDto;
import ir.maktab.cw29.mapper.LogMapper;
import ir.maktab.cw29.repository.LogRepository;
import ir.maktab.cw29.repository.LogRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LogServiceImpl implements LogService {
    private final LogRepository logRepository;
    private final LogRepositoryCustom logRepositoryCustom;
    private final UserService userService;
    private final LogMapper logMapper;

    @Override
    public void create(LogDto dto) {
        validateDto(dto);
        Log log = logMapper.mapToEntity(dto);
        logRepository.save(log);
    }

    @Override
    public List<RequestsPerUserDto> getCountByUser() {
        return logRepositoryCustom.findRequestNumbersPerUser();
    }

    @Override
    public List<UserRequestPerStatusDto> getByUserId(Long userId) {
        return logRepositoryCustom.findRequestsByUser(userId);
    }

    private void validateDto(LogDto dto) {
        userService.existsById(dto.getUserId());
    }
}
