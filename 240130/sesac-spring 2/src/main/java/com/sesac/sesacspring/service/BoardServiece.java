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
            //의존성을 주입받은 매퍼를 불러와야한다
    BoardMapper boardMapper;



    public List<BoardDTO> getBoardAll(){
        List<Board> result = boardMapper.getBoardAll();
        //실행하고 난 값을 받아온다
        List<BoardDTO> boards = new ArrayList<>();

        for( Board board: result){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardId(board.getId());
            boardDTO.setTitle(board.getTitle());

            boardDTO.setNo(100+ board.getId());
//            boardDTO.setContent(board.getContent());

            boards.add(boardDTO);

        }
        return boards;

    }

    public boolean insertBoard(BoardDTO boardDTO){
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        board.setWriter(boardDTO.getWriter());
        //domain에있는걸 담는것

        boardMapper.insertBoard(board);
        return true;
    }
}
