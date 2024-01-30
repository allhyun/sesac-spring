package com.sesac.sesacspring.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller

public class Prac2Controller {

    @GetMapping("/people")
    public String GetPeople(Model model){
//        String [ ] name = {"kim","lee","hong","park"};
//        int [] age = {10,20,30,40};
//        ArrayList<Person> people = new ArrayList<>();

        //하단에 클래스 생성해줌..
        ArrayList<Person1> people = new ArrayList<>();

        people.add(new Person1("kim",10));
        people.add(new Person1("lee",20));
        people.add(new Person1("hong",30));
        people.add(new Person1("park",40));

        model.addAttribute("people",people);

        Person1 p = new Person1("hhh",11);
        System.out.println(p.getName());
        //lombok ->




        return "people";
    }

    //
    @Getter
    @Setter
    public class Person1 {
        private String name;
        private int age;

//        public String getName() {
//            return name;
//        }
//
//        public void setName(String name) {
//            this.name = name;
//        }
//
//        public int getAge() {
//            return age;
//        }
//
//        public void setAge(int age) {
//            this.age = age;
//        }

        public Person1(String name, int age){
            this.name=name;
            this.age=age;
        }

    }
}
