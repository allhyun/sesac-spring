package com.sesac.sesacspring.mapper;

import com.sesac.sesacspring.domain.Board;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface BoardMapper {

    List<Board> getBoardAll(); //전체조회(select +formboard)

//    @Insert("insert into board(title,content,writer) values(#{title},#{content},#{writer})")
//    void createBoard(
//            String title, String content, String writer
//    );
    //sql을 작성하거나 , xml을 작성하거나
    // 1) mapper 에는 메소드가 있고, xml은 없는 경우 -> 실행했을때 오류
    // 2) xml에는 있고 mapper에는 없는경우 -> 실행자체가 안된다

    void insertBoard(Board board);


}
