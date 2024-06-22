package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void deleteUserById(int id);
    void updateUserById(User user, int id);
    List<User> listUsers();
    User getUserById(int id);
    User findByUsername(String username);
}
