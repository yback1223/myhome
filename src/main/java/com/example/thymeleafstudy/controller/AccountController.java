package com.example.thymeleafstudy.controller;

import com.example.thymeleafstudy.model.User;
import com.example.thymeleafstudy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

     private final UserService userService;

     @Autowired
     public AccountController(UserService userService) {
          this.userService = userService;
     }


     @GetMapping("/login")
     public String login() {
          return "account/login";
     }

     @GetMapping("/register")
     public String register() {
          return "account/register";
     }

     @PostMapping("/register")
     public String register(User user) {
          userService.save(user);
          return "redirect:/"; //homecontroller의 index메소드를 호출하는 것과 같으므로 제대로 index페이지로 간다.
     }
}
