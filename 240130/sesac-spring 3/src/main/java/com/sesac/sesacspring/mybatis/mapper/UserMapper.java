package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    // sql문을 정의 or xml 파일을 읽거나

    // xml 파일을 읽어서 실행
    List<User> retreiveAll();

    // sql문 정의
    @Insert("insert into user(name, nickname) values(#{name}, #{nickname})")
    void createUser(
            String name, String nickname);

}
