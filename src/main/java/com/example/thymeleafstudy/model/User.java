package com.example.thymeleafstudy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Entity //jpa에게 이게 모델 클래스다라는 것을 알려준다.
@Data
public class User {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String username;
     private String password;
     private Boolean enabled;

     @ManyToMany
     @JoinTable(
             name = "user_role",
             joinColumns = @JoinColumn(name = "user_id"),
             inverseJoinColumns = @JoinColumn(name = "role_id"))
     private List<Role> roles = new ArrayList<>();//user에 대한 권한이 roles에 담긴다.
}
