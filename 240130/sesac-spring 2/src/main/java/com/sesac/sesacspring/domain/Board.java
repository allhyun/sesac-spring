package com.sesac.sesacspring.domain;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Board {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Timestamp registered;
}
