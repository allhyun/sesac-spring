package com.sesac.sesacspring.mybatis.mapper;

import com.sesac.sesacspring.mybatis.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> retreiveAll();

    @Insert("INSERT INTO user(name, nickname) VALUES(#{name}, #{nickname})")
    void createUser(String name, String nickname);

}
