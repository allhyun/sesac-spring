package com.sesac.sesacspring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

//의존성을 부여받는 것

@Controller
//@Controller : 해당 클래스가 Controller의 역할을 하는 클래스라는 것을 알려준다
//Spring Container에게 알려준다
public class HelloController {

    //URL을 매핑시켜주는 친구
    //클라이언트가 '/hi'라는 경로로 GET method로
    //접근한다면 아래 메소드를 실행해라
    @GetMapping("/hi")
    public String getHi(Model model){
        //Model : COntrooler 안의 메서드가 파라미터로 받을 수 있는 객체 중 하나
        //Model 안에 정보를 담아서 view로 전달
        //IoC: 개발자가 직접 model을 생성 x

        model.addAttribute("name", "홍길동");
//        model.addAttribute("key","값");
        model.addAttribute("name2","<strong>코딩온</strong>");

        //실습
        model.addAttribute("age1",18);

//        String[] items = new String["a","n"];
        String[]x={"ａ","ｂ", "ｃ", "ｄ", "ｅ"};
        model.addAttribute("item1", x);
        char[] alphabetArray = new char[26];
        char alphabet = 'A';

        for (int i = 0; i < 26; i++) {
            alphabetArray[i] = alphabet;
            alphabet++;
        }
        model.addAttribute("item2", alphabetArray);




        return "hi";//템플릿 파일의 이름
        //res,render("hi")
        //res.render("hi","홍길동")
    }
}


