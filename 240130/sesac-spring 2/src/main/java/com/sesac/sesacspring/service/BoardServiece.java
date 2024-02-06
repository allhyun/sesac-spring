package com.sesac.sesacspring.service;


import com.sesac.sesacspring.domain.Board;
import com.sesac.sesacspring.dto.BoardDTO;
import com.sesac.sesacspring.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardServiece {
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDTO> reterieveAll(){
        List<Board> boards = boardMapper.retreiveAll();
        List<BoardDTO> result = new ArrayList<>();

        for( Board board: boards){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setTitle(board.getTitle());
            boardDTO.setContent(board.getContent());

            boardDTO.setWriter(board.getWriter());
//            boardDTO.setContent(board.getContent());

            result.add(boardDTO);

        }
        return result;

    }

    public void createBoard(
            String title, String content, String writer
    ){
        boardMapper.createBoard(title,content,writer);
    }

}
