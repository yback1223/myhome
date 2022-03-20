package com.example.thymeleafstudy.service;

import com.example.thymeleafstudy.model.Role;
import com.example.thymeleafstudy.model.User;
import com.example.thymeleafstudy.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {


     private final UserRepository userRepository;
     private final PasswordEncoder passwordEncoder;

     @Autowired
     public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
          this.userRepository = userRepository;
          this.passwordEncoder = passwordEncoder;
     }


     public User save(User user) {
          String encodedPassword = passwordEncoder.encode(user.getPassword());
          user.setPassword(encodedPassword);
          user.setEnabled(true);

          Role role = new Role();
          role.setId(1L);
          user.getRoles().add(role);

          return userRepository.save(user);
     }

}
