package lecture.springbootsecurity.config.jwt;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
@ConfigurationProperties("jwt") //어플리케이션 프로퍼티 jwt 관련 속성을 참고하여 , 해당 클래스의
public class JwtProperties {
    private String issuer;
    private String secretKey;
}
