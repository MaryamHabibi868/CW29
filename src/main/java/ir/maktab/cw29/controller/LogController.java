package ir.maktab.cw29.controller;

import ir.maktab.cw29.dto.RequestsPerUserDto;
import ir.maktab.cw29.dto.UserRequestPerStatusDto;
import ir.maktab.cw29.service.LogService;
import lombok.RequiredArgsConstructor;
import org.bson.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/log")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @GetMapping("/count-request-by-user")
    public ResponseEntity<List<RequestsPerUserDto>> getCountRequestByUser() {
        return ResponseEntity.ok(
                logService.getCountByUser()
        );
    }

    @GetMapping("/user/{id}/requests")
    public ResponseEntity<List<UserRequestPerStatusDto>> getCByUser(@PathVariable Long id) {
        return ResponseEntity.ok(
                logService.getByUserId(id)
        );
    }
}
