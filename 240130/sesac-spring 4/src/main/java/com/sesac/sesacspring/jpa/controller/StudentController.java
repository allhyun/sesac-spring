package com.sesac.sesacspring.jpa.controller;

import com.sesac.sesacspring.jpa.dto.StudentDTO;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
//    @GetMapping("/count")
//    public int getCountAll(){}

    // 1. 전체 검색 ( select * from student )
    @GetMapping("/all")
    public List<StudentDTO> getAll(){
        // student의 목록을 전부 가져와서 보여주는 api
        List<StudentDTO> result = studentService.getStudentAll();
        return result;
//        return studentService.getStudentAll();
    }
    // 2. 삽입 ( insert into ~~~ )
    @GetMapping("/insert") // /student/insert?name=이름
    public String insertStudent(@RequestParam String name,
                                @RequestParam String nickname,
                                @RequestParam Student.LoginType type) {
        return studentService.insertStudent(name, nickname, type);
        // 이름, 닉네임, login_type
    }
    // 3. 조건에 따른 검색 ( select * from student where name='')
    @GetMapping("/search/name") // /search/name?name=이름
    public String searchStudentByName(@RequestParam String name){
        return studentService.searchStudentByName(name);
    }
    // 4. 조건에 따른 검색 (2) select * from student where id = )
    @GetMapping("/search/id")
    public String searchStudentById(@RequestParam int id) {
        return studentService.searchStudentById(id);
    }

//    @GetMapping("/search")
//    public ? getSearch(@RequestBody int id){}

    @GetMapping("/count") // /student/count?nickname=닉네임
    public int searchCntByNickname(@RequestParam String nickname) {
        return studentService.searchCntByNickname(nickname);
    }

    @GetMapping("/update")
    public String updateStudent(@RequestParam int id,
                                @RequestParam String name){
        // id : 내가 변경할 데이터의 primary key
        // name : 내가 변경하고자 하는 name 컬럼의 값
        return studentService.updateStudent(id, name);
    }
}
