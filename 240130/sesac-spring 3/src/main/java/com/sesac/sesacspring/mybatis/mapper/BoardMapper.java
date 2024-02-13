package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    List<Board> getBoardAll(); // 전체 조회 ( select * from board; )
    void insertBoard(Board board); // 삽입 ( insert )
//    void createUser(String name, String age)
    void patchBoard(Board board); //수정( update)

    void deleteBoard(int id); // 삭제 (delete)

    List<Board> searchBoard(String word);
}
// sql 을 작성하거나, xml 파일을 작성하거나
// 1) mapper에는 메소드가 있고, xml에는 없는 경우  -> 실행했을 때 오류
// 2) xml 에는 있고, mapper에는 없는 경우 -> 실행 자체가 안 된다.