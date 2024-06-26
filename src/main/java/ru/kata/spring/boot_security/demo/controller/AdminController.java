package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final UserService userService;
    final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("activeTab", "usersTable");
        model.addAttribute("roles", roleService.listRoles());
        return "users/admin";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("activeTab", "addUser");
        return "users/admin";
    }

    @PostMapping()
    public String create(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", roleService.listRoles());
        return "users/admin";
    }

    @PostMapping("/edit")
    public String update(User user, @RequestParam("id") int id) {
        userService.updateUserById(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }
}
