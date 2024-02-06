package com.sesac.sesacspring.service;

import com.sesac.sesacspring.domain.User;
import com.sesac.sesacspring.dto.UserDTO;
import com.sesac.sesacspring.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    //UserMapper 호출
    //1. 생성자 사용
//    private final UserMapper userMapper;
//    public UserService(UserMapper userMapper){
//        this.userMapper = userMapper;
//    }
    //2. @Autowired
    @Autowired
    UserMapper userMapper;

    public List<UserDTO> retrieveAll(){
        //contrroller 에서 호출하는 메서드
        // usermapper의 retrieveAll()을 실행한 후 받아온 list<User>
        //List<UserDTO>에 담아서 반환
       List<User> users = userMapper.retreiveAll();
       List<UserDTO> result = new ArrayList<>();
//     //List 는 인터페이스라 new안됩니다!




        //for문을 이용해 List<user> -> List<UserDTO>
        for( User user: users){
            UserDTO userDTO = new UserDTO();
            userDTO.setName(user.getName());
            userDTO.setNickname(user.getNickname());

            result.add(userDTO);
        }

        //전체동작흐름
        //1) userService.retireveAll()에서 의존성을 주입받은
        //2) userMapper interface 파일에서 xml파일 확인 필요 여부 체크
        //3) 정의된 mapper폴더(application.properties에서 정의 에서 namespace가UserMApper 인 xml검색
        //4)id가 retrieveAll인 태그를 찾고 그안의 sql문을 실행
        //5)실행결과를 resultType에 정의된 객체에 매핑해서 반환

        return result;
    }

    public void createUser(
            String name, String nickname
    ){
        userMapper.createUser(name,nickname);

    }

    public void updateUser(
        int id, String nickname
    ){
        userMapper.updateUser(id,nickname);
    }

    public void deleteUser(
             int id
    ){
        userMapper.deleteUser(id);
    }
}
