package com.sesac.sesacspring.mapper;

import com.sesac.sesacspring.domain.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {

    List<Board> retreiveAll();

    @Insert("insert into board(title,content,writer) values(#{title},#{content},#{writer})")
    void createBoard(
            String title, String content, String writer
    );


}
