package com.ichtus.braingame.multiplication_old.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<User> getUser(@RequestParam String userAlias) {
        return ResponseEntity.ok(userService.findByAlias(userAlias));
    }

    @PostMapping
    ResponseEntity<User> createNewUser(@RequestBody @Valid UserDTO userDTO) {
        log.info("adding new user in UserController with name = " + userDTO.getAlias());
        return ResponseEntity.ok(userService.addUser(userDTO.getAlias()));
    }
}
