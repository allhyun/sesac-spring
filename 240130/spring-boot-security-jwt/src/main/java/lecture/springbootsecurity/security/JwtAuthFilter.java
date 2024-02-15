package lecture.springbootsecurity.security;

//1. 세션 기반 인증 방식
//--로그인 성송 > session에 userId저장

//2. JWT 토큰기반 인증 방식
// -- 로그인 성공> 서버에서 jwt토큰 발급 > 응답에 token을 같이보냄
// -- 클라이언트는 브라우저에 token을 저장(보통은 localstorage에 저장)
// --   클라이언트에서 로그인이 필요한 요청을 보낼때 token을 header 의 Auth~에 담아서 보냄
// -- 서버에서 요청 객체의 heade>Auth!정보에서 token을 가져옴

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

//OncePerRequestFilter : 요청 당 한 번만 싫행한다.
//
@Slf4j
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    TokenProvider tokenProvider;
    //서버에서 요청 객체의 header > Suth정보에서 token을 가져옴 > 토큰이 유효한 지 검증
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);
        //token값이 널인지 아닌지 equalsIgnoreCase문자열 비교시 사용 대문자 소문자도 구분한다

        try {
            if (token != null && !token.equalsIgnoreCase("null")) { //token.equalsIgnoreCase("null") null값인지 아닌지 구분해냄
                String userId = tokenProvider.validateAndGetUserId(token);

                // 1. 사용자 정보를 담는 공간? 토큰 생성
                Authentication authentication = new UsernamePasswordAuthenticationToken(String.valueOf(userId), null, AuthorityUtils.NO_AUTHORITIES);

                // 2. SecurityContextHolder 에 authentication 정보 set
                // SecurityContextHolder : 클라이언트의 요청 -> 응답 사이에 일시적으로 auth 정보를 저장할 수 있는 공간
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }catch (Exception e){
            log.error("auth error ", e.getMessage());
        }

        //무조건 실행 시키도록
        filterChain.doFilter(request,response);
    }

    //토큰을 헤더에서 가져오는 작업 + TokenProvider 에 있는 메소드 사용해서 payload 값(userId) 추출
    //포스트맨에서 토큰확인시
    public String getBearerToken(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        //"Baerer sldkfjdngdfns,mgndf"

        //StringUtils.hasText(param) : param 이 null인지 아닌지, 길이가 0보다 큰지
        //startsWith("~"): ~로 시작하는 걸로 시작하는지 검증하기위해
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
            //substring(숫자) : 앞에 몇글자를 떼어낼것인가
            //즉 토큰값만 가져오게

        }

        return null;
    }
}
