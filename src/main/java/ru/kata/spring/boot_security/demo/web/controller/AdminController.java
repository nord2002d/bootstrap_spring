package ru.kata.spring.boot_security.demo.web.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.web.model.User;
import ru.kata.spring.boot_security.demo.web.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class AdminController {
    private UserService userService;
    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String startPage(){
        return "login";
    }
    @GetMapping("/admin")
    public String adminPage( Principal principal, ModelMap model) throws ParseException, JsonProcessingException {
        String email = userService.getEmailAuthentication(principal);
        User user = userService.findByEmailLike(email);
        model.addAttribute("adminUser",user);
        model.addAttribute("admin",userService.listUsers());
        return "admin";
    }

}
