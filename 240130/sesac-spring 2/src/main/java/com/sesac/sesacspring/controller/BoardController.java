package com.sesac.sesacspring.controller;


import com.sesac.sesacspring.dto.BoardDTO;
import com.sesac.sesacspring.service.BoardServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    BoardServiece boardServiece;
    @GetMapping("/list")
    public String getboardMain(){return "board";}
    @GetMapping("/all")
    public List<BoardDTO> getAllBoard(Model model){
        List<BoardDTO> result = boardServiece.reterieveAll();
        model.addAttribute("boards",result);
        return result;
    }

    @PostMapping("/board")
    @ResponseBody
    public String  getBoardCreate(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String writer

    ){boardServiece.createBoard(title,content,writer);
        return "redirect:/list";
    }
}
