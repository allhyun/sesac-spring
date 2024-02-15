package lecture.springbootsecurity.security;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.*;
import io.jsonwebtoken.SignatureAlgorithm;
import lecture.springbootsecurity.config.jwt.JwtProperties;
import lecture.springbootsecurity.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

//토큰 발급하는 메소드 만들기
//토큰 검증하는 메소드 만들기

//발급하는 메소드 -> 로그인 성공하는 곳
@Component //어노테이션 달고 UserController에서 사용하기위해
public class TokenProvider {
    @Autowired
    JwtProperties jwtProperties; //필드 만들기

    public String create(UserEntity userEntity) {
        //만료날짜 정의
        Date expiredate = Date.from(Instant.now().plus(1, ChronoUnit.DAYS));

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecretKey()) //signWith(secretKEY, 암호화 방식)을 설정
                .setSubject(String.valueOf(userEntity.getId())) //payload에 들어갈 정보
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(new Date())
                .setExpiration(expiredate)
                .compact();

    }
    //  토큰을 검증하는 메소드
    public String validateAndGetUserId(String token){
        Claims claims =Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token) //검증할 토큰 설정
                .getBody(); //patloadf를  get하는 메소드

        return claims.getSubject();
    }

}
