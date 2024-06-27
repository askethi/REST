package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.service.UserService;

@Controller
@RequestMapping("/")
public class UserControllerBU {

    final UserService userService;

    @Autowired
    public UserControllerBU(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String show(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "user";
    }
}