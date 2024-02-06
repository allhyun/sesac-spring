package com.sesac.sesacspring.api.controller;

import lombok.Getter;
import lombok.Setter;

// @Getter
// @Setter
@Getter
@Setter
public class Person {
    private String name;
    private int age;
    // lombok
    public Person(String name, int age){
        this.name = name;
        this.age = age;
    }

}