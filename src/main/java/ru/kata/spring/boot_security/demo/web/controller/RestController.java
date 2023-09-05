package ru.kata.spring.boot_security.demo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.exeptions.UserDeleteException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import java.security.Principal;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("admin/form")
public class RestController {
    private UserService userService;
    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public ResponseEntity<User>getUser(@PathVariable long userId) throws UserNotFoundException {
        return ResponseEntity.ok(userService.getUser(userId));
    }
    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody User user) throws UserEmailException {
        return ResponseEntity.ok(userService.add(user));
    }

    @PatchMapping
    public ResponseEntity<User> updateUser(@RequestBody User user) throws UserEmailException {
        return ResponseEntity.ok(userService.update(user));
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody User user, Principal principal) throws ParseException, JsonProcessingException, UserDeleteException {
        userService.removeUser(user,principal);
        return ResponseEntity.noContent().build();
    }
}
