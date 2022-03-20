package com.example.thymeleafstudy.controller;

import com.example.thymeleafstudy.model.Board;
import com.example.thymeleafstudy.repository.BoardRepository;
import com.example.thymeleafstudy.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {


     private final BoardRepository boardRepository;
     private final BoardValidator boardValidator;

     @Autowired
     public BoardController(BoardRepository boardRepository, BoardValidator boardValidator) {
          this.boardRepository = boardRepository;
          this.boardValidator = boardValidator;
     }

     @GetMapping("/list")
     public String list(Model model, @PageableDefault(size = 2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText) {
          Page<Board> boards = boardRepository.findByTitleOrContentContaining(searchText, searchText, pageable); //jpa 0페이지 시작
//          Page<Board> boards = boardRepository.findAll(pageable); //jpa 0페이지 시작
          int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4);
          int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
          model.addAttribute("startPage", startPage);
          model.addAttribute("endPage", endPage);
          model.addAttribute("boards", boards);
          return "board/list";
     }

     @GetMapping("/form")
     public String form(Model model, @RequestParam(required = false) Long id) {
          if (id == null) {
               model.addAttribute("board", new Board());

          } else {
               Board board = boardRepository.findById(id).orElse(null);
               model.addAttribute("board", board);
          }
          return "board/form";
     }

     @PostMapping("/form")
     public String boardSubmit(@Valid Board board, BindingResult bindingResult) {
          boardValidator.validate(board, bindingResult);

          if (bindingResult.hasErrors()) {
               return "board/form";
          }
          boardRepository.save(board);
          return "redirect:/board/list"; //redirect를 활용해서 @GetMapping("/list")로 이동한다.
     }
}
