package com.sesac.sesacspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//의존성을 부여받는 것

@Controller
//@Controller : 해당 클래스가 Controller의 역할을 하는 클래스라는 것을 알려준다
//Spring Container에게 알려준다
public class HelloController {

    //URL을 매핑시켜주는 친구
    //클라이언트가 '/hi'라는 경로로 GET method로
    //접근한다면 아래 메소드를 실행해라
    @GetMapping("/hi")
    public String getHi(){
        return "hi";//템플릿 파일의 이름
        //res,render("hi")
    }
}
