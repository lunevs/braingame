package com.ichtus.braingame.multiplication.challenge;

import com.ichtus.braingame.multiplication.user.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<User> createNewUser(@RequestBody @Valid UserDTO userDTO) {
        log.info("adding new user in UserController with name = " + userDTO.getAlias());
        return ResponseEntity.ok(userService.addUser(userDTO.getAlias()));
    }
}
