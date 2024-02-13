package com.sesac.sesacspring.dto;


import lombok.Getter;
import lombok.Setter;


// 필드명이 데이터랑 같을필요가 없음
@Getter
@Setter
public class BoardDTO {
    private int boardId;
    private String title;
    private String content;
    private String writer;
    private String registered;
    private int no;

}
