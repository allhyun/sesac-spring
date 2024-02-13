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

    // 5개의 method
    // 1. 전체조회 -> board.thml 렌더링
    // 2. 작성 (create) : axios(동적폼전송) =@RequestBody
    // 3. 수정( update) : axios (동적폼전송)
    // 4. 삭제(delete) :axios (동적폼전송)
    // 5. 검색(조회) :acios (동적폼전송 ,get)

    // 1.전체조회
    @GetMapping("/board/mybatis")
    public String getBoard(){
        return "board";
    }



    @Autowired
    BoardServiece boardServiece;

    @GetMapping("")
    @ResponseBody
    public String  getBoard(Model model)
    {List<BoardDTO> result = boardServiece.getBoardAll();
        model.addAttribute("list",result);

        return "board";
    }

    @PostMapping("" ) // '/board/mybatis'
    @ResponseBody
    public boolean insertBoard(@RequestBody BoardDTO boardDTO){
        //2.게시글 작성
        boardServiece.insertBoard(boardDTO);
        return true;
    }

}
