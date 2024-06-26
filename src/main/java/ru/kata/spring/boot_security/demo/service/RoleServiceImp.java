package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class RoleServiceImp implements RoleService {


    private final RoleRepository roleRepository;

    @Autowired
    RoleServiceImp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    @Override
    public void add(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    @Override
    public void deleteRoleById(int id) { roleRepository.deleteById(id); }

    @Override
    public List<Role> listRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    @Override
    public void updateRoleById(Role role, Integer id) {
        role.setId(id);
        roleRepository.save(role); }

    @Override
    public Role getRoleById(int id) {
        Optional<Role> user = roleRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public Role findByRole(String role) {
        return roleRepository.findByRole(role);
    }

}