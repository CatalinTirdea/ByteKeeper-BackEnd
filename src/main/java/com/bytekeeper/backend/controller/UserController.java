package com.bytekeeper.backend.controller;

import com.bytekeeper.backend.model.DTO.InventoryDTO;
import com.bytekeeper.backend.model.DTO.UserDTO;
import com.bytekeeper.backend.model.User;
import com.bytekeeper.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequestMapping(value="/users")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value="/register", consumes="application/json", produces="application/json")
    public ResponseEntity<?> register(@RequestBody UserDTO user) {
        userService.addUser(new User(
                user.getMail(),
                user.getName(),
                user.getPassword()
        ));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping(value="/login", consumes="application/json", produces="application/json")
    public ResponseEntity<Long> login(@RequestBody UserDTO user) {
        Optional<User> u = userService.getUserByMail(user.getMail());
        if (u.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        if (!userService.checkPassword(u.get(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(userService.getUserIdByMail(user.getMail()));
    }

}
