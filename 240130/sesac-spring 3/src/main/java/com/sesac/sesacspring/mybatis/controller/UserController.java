package com.sesac.sesacspring.mybatis.controller;

import com.sesac.sesacspring.mybatis.dto.UserDTO;
import com.sesac.sesacspring.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// Mybatis 수업 controller
@RestController
@RequestMapping("/user") //
public class UserController {
    // C, R
    // 1. Table 생성 완료 ( user )
    // 2. domain 만들기 ( domain/user )
    // 3. mapper 만들기 
    // 4. service 만들기
    // 5. controller 만들기

    @Autowired
    UserService userService;

    @GetMapping("/all") // /user/all
    public List<UserDTO> getUser(){
        List<UserDTO> result = userService.retrieveAll();
        return result;
    } // []

    @GetMapping("/user") // /user/user
    public String getUserInsert(
            @RequestParam String name,
            @RequestParam String nickname) {
        userService.createUser(name, nickname);
        return "Success";
    }
}
