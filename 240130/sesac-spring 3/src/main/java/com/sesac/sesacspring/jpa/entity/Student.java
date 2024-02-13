package com.sesac.sesacspring.jpa.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.annotation.processing.Generated;

@Entity // : 데이터베이스의 필드와 변수의 연관관계가 정의된 친구 // class Student(){} // 빈 생성자가 필수로 필요하다.
       // : db테이블에 대응되는 하나의 클래스
@NoArgsConstructor
@Getter
@Builder //필드가 전체가 다 들어있는 생성자가 필수로 필요하다.
@AllArgsConstructor //@builder 없으면 필요없음
@Table(name="studentAll")

public class Student {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int id;
    //id int primary key auto_increment
    //SeQUENCE: 새로운 오브젝트를 만들어서 id를 부여하는 방법( mysql x)
    //TABLE: SEQUENCE 전략을 흉내낸 전략 => 모든 DBMS에서 사용 가능


    @Column(name="name", nullable=false,length = 20)
    private String name;
    //name varchar(20) not null

    @Column (columnDefinition = "TEXT")
    private String nickname;

    @Column
    @Enumerated(EnumType.STRING)
    private LoginType type;

    public enum LoginType{
        GOOGLE,KAKAO
    }

}
