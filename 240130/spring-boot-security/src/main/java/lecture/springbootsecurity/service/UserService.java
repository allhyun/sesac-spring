package lecture.springbootsecurity.service;

import lecture.springbootsecurity.entity.UserEntity;
import lecture.springbootsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    //암호화 객체 생성
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    //유저레포지토리 이용해서 객체 생성

    public UserEntity create(UserEntity userEntity){//회원가입 메소드
        if(userEntity == null ){
            throw new RuntimeException("entity null");
        }

        //중복 이메일 불가
        String email = userEntity.getEmail();

        if(userRepository.existsByEmail(email)){
            throw new RuntimeException("이미 존재하는 이메일");

        }
        return userRepository.save(userEntity);
    }

//    암호화 하기 전 코드

//    public UserEntity login(String email, String password){
//        return userRepository.findByEmailAndPassword(email,password);

// 암호화 한 코드
        public UserEntity login(String email, String password){
        UserEntity searchUser = userRepository.findByEmail(email);


        //String password 와 findByEmail(email) 를 비교해서 맞는지 확인하는 코드
        if(searchUser != null && passwordEncoder.matches(password, searchUser.getPassword()));
        return searchUser;
    }

}
