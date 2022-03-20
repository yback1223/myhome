package com.example.thymeleafstudy.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity //jpa에게 이게 모델 클래스다라는 것을 알려준다.
@Data
public class Role {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     private String name;

     @ManyToMany(mappedBy = "roles")
     private List<User> users;

}
