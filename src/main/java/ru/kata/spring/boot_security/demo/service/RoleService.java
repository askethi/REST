package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    void add(Role role);
    void deleteRoleById(int id);
    List<Role> listRoles();
    void updateRoleById(Role role, Integer id);
    Role getRoleById(int id);
    Role findByRole(String role);
}
