package com.sesac.sesacspring.mybatis.service;

import com.sesac.sesacspring.mybatis.domain.Board;
import com.sesac.sesacspring.mybatis.dto.BoardDTO;
import com.sesac.sesacspring.mybatis.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {
    @Autowired
    BoardMapper boardMapper;

    public List<BoardDTO> getBoardAll(){
        List<Board> result = boardMapper.getBoardAll();
        List<BoardDTO> boards = new ArrayList<>();

        for ( Board board : result ){
            BoardDTO boardDTO = new BoardDTO();
            boardDTO.setBoardID(board.getId());
            boardDTO.setTitle(board.getTitle());
            boardDTO.setContent(board.getContent());
            boardDTO.setWriter(board.getWriter());
            boardDTO.setRegistered(board.getRegistered());
            boardDTO.setNo(100 + board.getId());
            boards.add(boardDTO);
        }
        return boards;
    }
    public boolean insertBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());

        boardMapper.insertBoard(board);
        return true;
    }

    public void patchBoard(BoardDTO boardDTO) {
        // board.getBoardID // title, content, writer
        Board board = new Board();
        board.setId(boardDTO.getBoardID()); // update의 where
        board.setTitle(boardDTO.getTitle());
        board.setContent(boardDTO.getContent());
        board.setWriter(boardDTO.getWriter());
        boardMapper.patchBoard(board);
    }
    public void deleteBoard(int id){
        boardMapper.deleteBoard(id);
    }

    public int searchBoard(String word) {
        List<Board> result = boardMapper.searchBoard(word);
        return result.size();
    }
}
