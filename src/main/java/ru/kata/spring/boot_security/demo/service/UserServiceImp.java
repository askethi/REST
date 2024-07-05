package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{


   private final UserRepository userRepository;
   private final PasswordEncoder passwordEncoder;
   private final RoleService roleService;

   @Autowired
   UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleService roleService) {
      this.userRepository = userRepository;
      this.passwordEncoder = passwordEncoder;
      this.roleService = roleService;
   }

   @Transactional
   @Override
   public void add(User user) {
      user.setRoles(user.getRoles().stream()
              .map(role -> roleService.findByRole(role.getRole()))
              .collect(Collectors.toSet()));
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional
   @Override
   public void deleteUserById(int id) { userRepository.deleteById(id); }


   @Override
   public List<User> listUsers() {
      return userRepository.findAll();
   }

   @Transactional
   @Override
   public void updateUser(User user) {
      user.setRoles(user.getRoles().stream()
              .map(role -> roleService.findByRole(role.getRole()))
              .collect(Collectors.toSet()));
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }

   @Transactional
   @Override
   public void updateUserById(User user, int id) {
      user.setId(id);
      user.setPassword(passwordEncoder.encode(user.getPassword()));
      userRepository.save(user);
   }

   @Override
   public User getUserById(int id) {
      Optional<User> user = userRepository.findById(id);
      return user.orElse(null);
   }

   @Override
   public User findByUsername(String username) {
      return userRepository.findByUsername(username);
   }
}
