package lecture.springbootsecurity.controller;

import jakarta.servlet.http.HttpSession;
import lecture.springbootsecurity.dto.UserDto;
import lecture.springbootsecurity.entity.UserEntity;
import lecture.springbootsecurity.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.HttpSessionRequiredException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j //로그를 찍는다 어노테이션.. 로그관련 메소드를 편리하게 사용할 수 있음..
public class UserController {
    @Autowired
    UserService userService;


    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @GetMapping("")
    public String getAuth() {
        return "GET/auth";
    }

    @PostMapping("/signup")
    //해당 객체를 리턴하는 ... 응답객체를 리턴해줘야함 자바객체 말고!(자바객체 ex. UserEntity...등)
    //ResponseEntitiy 객체를 만들어서 리턴해주는게 좋다
    //    ? : 와일드카드(어떤 값을 body에 담을지 모름)
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto){
        //예외코드
        try{

            UserEntity user =UserEntity.builder()
                    .email(userDto.getEmail())
                    .username(userDto.getUsername())
//                    .password(userDto.getPassword())
                    .password(passwordEncoder.encode(userDto.getPassword())) //암호화
                    .build();


            UserEntity responseUser =userService.create(user);

            UserDto responseUserDto = userDto.builder()
                    .email(responseUser.getEmail())
                    .username(responseUser.getUsername())
                    .id(responseUser.getId())
                    .password(responseUser.getPassword())
                    .build();
            return ResponseEntity.ok().body(responseUserDto);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PostMapping("/signin")
    public ResponseEntity<?> loginUser(HttpSession session, @RequestBody UserDto userDTO) {
        try {
            UserEntity user = userService.login(userDTO.getEmail(), userDTO.getPassword());

            if(user == null) {
                throw new RuntimeException("login failed");
            }

            UserDto responseUserDTO = UserDto.builder()
                    .email(user.getEmail())
                    .username(user.getUsername())
                    .id(user.getId())
                    .build();

            //log.info
            //log.error()
            log.warn("session id{}",session.getId());

            session.setAttribute("userId", user.getId());




            return ResponseEntity.ok().body(responseUserDTO);
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}