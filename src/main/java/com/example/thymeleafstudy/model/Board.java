package com.example.thymeleafstudy.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;


@Entity //jpa에게 이게 모델 클래스다라는 것을 알려준다.
@Data
public class Board {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;

     @NotNull
     @Size(min=2, max=30, message = "제목은 2자 이상 3자 이하입니다.")
     private String title;

     private String content;
}
