package lecture.springbootsecurity.security;
//1. 세션 기반 인증 방식
//->로그인에 성공ㅎ사 session에 userId저장
//-> 로그인 여부 판당하고 싶을때 > session에 userId가 있는지 없는지
//------존재하면, 로그인을 한 사람, 존재하지않으면 로그인을 하지 않은 사람



//2. jwt token  기반 인증 방식도 해볼예정

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Slf4j
public class CustomAuthFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 로그인 여부 판당하고 싶을때 코드 구현
        try{
            HttpSession session = request.getSession();
            log.warn("sesion id {}", session.getId());
            Object userId = session.getAttribute("userId");

            if(userId != null) {

                //1. 사용자 정보를 담는 공간? 토큰 생성
                Authentication authentication = new UsernamePasswordAuthenticationToken(String.valueOf(userId),null, AuthorityUtils.NO_AUTHORITIES);
                //String.valueOf(userId) 에 () 안에는 식별하고싶은 값을 넣어라..

                //2. SecurityContextHolder 에  authentication 정보 set
                // SecurityContextHolder는 클라이언트가 요청을하고 응답을하기 전까지 일시적으로 auth정보를 담아놓는 공간이다..
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch(Exception e){

            log.error("filter error {}", e.getMessage());
        }

        filterChain.doFilter(request,response);
    }
}
