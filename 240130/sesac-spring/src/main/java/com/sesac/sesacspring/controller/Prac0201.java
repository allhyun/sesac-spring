package com.sesac.sesacspring.controller;

import com.sesac.sesacspring.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Prac0201 {
    @GetMapping("/introduce")
    public String getMain(){ return "introduce"; }

    @GetMapping("/introduce/{param1}")
    public String getPrac(
            @PathVariable String param1,
            Model model
    ){
        model.addAttribute("name",param1);
        return "introduce";}

    @GetMapping("/introduce2")
    public String getPrac1(
            @RequestParam(value="name") String i,
            @RequestParam(value="age") String a,
            Model model
    ){
        model.addAttribute("name",i);
        model.addAttribute("age",a);
        return "introduce";}


    @PostMapping("/post/prac")
    @ResponseBody
    public String postPrac(
            @RequestParam(value="name", required = false) String a,
            @RequestParam(name="gender", required = false) String b,
            @RequestParam(value="year", required = false) String c,
            @RequestParam(value="month", required = false) String d,
            @RequestParam(value="day", required = false) String e,
            @RequestParam(name="interest", required = false) String f,
            Model model

    ){
        model.addAttribute("name",a);
        model.addAttribute("gender",b);
        model.addAttribute("year",c);
        model.addAttribute("month",d);
        model.addAttribute("day",e);
        model.addAttribute("interest",f);

        return a+"\n"+"\n"+b+"\n"+c+"\n"+"\n"+"\n"+d+"\n"+e+"\n"+f;


    }



    @PostMapping("axios/post/introduce")
    @ResponseBody
    public String axiosVoPrac(
            @RequestBody UserVO userVO
            ){
        return userVO.getName()+"회원가입 성공";

    }
}
