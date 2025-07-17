package ir.maktab.cw29.controller;

import ir.maktab.cw29.domain.User;
import ir.maktab.cw29.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(
            @RequestParam String username,
            @RequestParam String password) {

        return ResponseEntity.ok(userService.registerUser(username, password));
    }
}
