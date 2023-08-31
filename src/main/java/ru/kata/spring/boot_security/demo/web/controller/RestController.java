package ru.kata.spring.boot_security.demo.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.web.exeptions.UserEmailException;
import ru.kata.spring.boot_security.demo.web.exeptions.UserNotFoundException;
import ru.kata.spring.boot_security.demo.web.model.Role;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("admin/form")
public class RestController {
    private UserService userService;
    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("{userId}")
    public  User getUser(@PathVariable long userId) throws UserNotFoundException {
        return userService.getUser(userId);
    }
    @PostMapping
    public User addUser(@RequestBody User user) throws UserEmailException {
        User addUser = new User();
        addUser.setUsername(user.getUsername());
        addUser.setSurName(user.getSurName());
        addUser.setAge(user.getAge());
        addUser.setEmail(user.getEmail());
        addUser.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        addUser.setRoles(user.getRoles());
        return userService.add(addUser);
    }

    @PatchMapping
    public User updateUser(@RequestBody User user) throws UserEmailException {
        User userUpdate = new User();
        userUpdate.setId(user.getId());
        userUpdate.setUsername(user.getUsername());
        userUpdate.setSurName(user.getSurName());
        userUpdate.setAge(user.getAge());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setPassword(user.getPassword());
        userUpdate.setRoles(user.getRoles());
        return userService.update(userUpdate);
    }

    @DeleteMapping
    public User deleteUser(@RequestBody User user) throws UserNotFoundException {
        userService.removeUser(user.getId());
        return new User();
    }
}
