package com.example.thymeleafstudy.controller;


import com.example.thymeleafstudy.model.Board;
import com.example.thymeleafstudy.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardAPIController {

     private final BoardRepository boardRepository;

     @Autowired
     public BoardAPIController(BoardRepository boardRepository) {
          this.boardRepository = boardRepository;
     }

     @GetMapping("/boards")
     List<Board> all(@RequestParam(required = false, defaultValue = "") String title, @RequestParam(required = false, defaultValue = "") String content) {
          if (StringUtils.isEmpty(title) && StringUtils.isEmpty(content)) {
               return boardRepository.findAll();
          } else {
               return boardRepository.findByTitleOrContent(title, content);
          }
     }

     @PostMapping("/boards")
     Board newBoard(@RequestBody Board newBoard) {
          return boardRepository.save(newBoard);
     }

     @GetMapping("/boards/{id}")
     Board one(@PathVariable Long id) {
          return boardRepository.findById(id).orElse(null);
     }

     @PutMapping("/boards/{id}")
     Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) { //@RequestBody: 요청을 받는 파라미터
          return boardRepository.findById(id)
                  .map(board -> {
                       board.setTitle(newBoard.getTitle());
                       board.setContent(newBoard.getContent());
                       return boardRepository.save(board);
                  })
                  .orElseGet(() -> {
                       newBoard.setId(id);
                       return boardRepository.save(newBoard);
                  });
     }

     @DeleteMapping("/boards/{id}")
     void deleteBoard(@PathVariable Long id) {
          boardRepository.deleteById(id);
     }
}