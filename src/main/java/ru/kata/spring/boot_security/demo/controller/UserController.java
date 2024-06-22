package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserServiceImp;

import java.security.Principal;

@Controller
public class UserController {

    final UserServiceImp userService;
    final RoleService roleService;

    @Autowired
    public UserController(UserServiceImp userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String show(@RequestParam("username") String username, Model model) {
        model.addAttribute("user", userService.findByUsername(username));
        return "user";
    }

    @GetMapping("/admin")
    public String index(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.listUsers());
        model.addAttribute("activeTab", "usersTable");
        model.addAttribute("roles", roleService.listRoles());
        return "users/admin";
    }

    @GetMapping("/admin/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        //model.addAttribute("roles", roleService.listRoles());
        model.addAttribute("activeTab", "addUser");
        return "users/admin";
    }

    @PostMapping("/admin")
    public String create(@ModelAttribute User user) {
        userService.add(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String edit(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.getUserById(id));
        //model.addAttribute("roles", roleService.listRoles());
        return "users/admin";
    }

    @PostMapping("/admin/edit")
    public String update(User user, @RequestParam("id") int id) {
        userService.updateUserById(user, id);
        return "redirect:/admin";
    }

    @GetMapping("/admin/show")
    public String show(@RequestParam("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @PostMapping("/admin/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUserById(id);
        return "redirect:/admin";
    }

//    @GetMapping("/admin")
//    public String adminPage() {
//        return "users/admin";
//    }
}
