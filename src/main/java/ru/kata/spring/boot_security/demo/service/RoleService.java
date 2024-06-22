package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    private final RoleRepository roleRepository;

    @Autowired
    RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRoleById(int id) { roleRepository.deleteById(id); }

    @Transactional(readOnly = true)
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public void updateRoleById(Role role, Integer id) {
        role.setId(id);
        roleRepository.save(role); }

    @Transactional
    public Role getRoleById(int id) {
        Optional<Role> user = roleRepository.findById(id);
        return user.orElse(null);
    }

    //@Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

}