package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.repositories.UserRepository;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class UserServiceImp implements UserService{


   private final UserRepository userRepository;

   @Autowired
   UserServiceImp(UserRepository userRepository) {
      this.userRepository = userRepository;
   }

   @Transactional
   @Override
   public void add(User user) {
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
   public void updateUserById(User user, int id) {
      user.setId(id);
      userRepository.save(user); }

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
