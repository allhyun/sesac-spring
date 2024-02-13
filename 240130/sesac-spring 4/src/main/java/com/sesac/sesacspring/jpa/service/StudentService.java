package com.sesac.sesacspring.jpa.service;

import com.sesac.sesacspring.jpa.dto.StudentDTO;
import com.sesac.sesacspring.jpa.entity.Student;
import com.sesac.sesacspring.jpa.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<StudentDTO> getStudentAll(){
        List<Student> result = studentRepository.findAll();
        List<StudentDTO> students = new ArrayList<>();

        for ( Student student: result ){
            // Builder : 객체를 만들 때 순서에 의해 생기는 문제, 
            //           명시적이지 못한 문제 를 해결하기 위해 나온 방법
            // 생성자 주입 : 여러개의 필드가 있을 때 순서를 지켜줘야 한다.
            // setter : 필드 개수만큼 매번 메소드를 만들어줘야 한다.
            StudentDTO studentDTO = StudentDTO.builder() // new Builder
                    .nickname(student.getNickname())
                    .name(student.getName())
                    .build();
            students.add(studentDTO);
        }

        return students;
    }

    public String insertStudent(String name, String nickname, Student.LoginType type){
        // 받아온 데이터로 repository의 save 메소드를 호출
        Student student = Student.builder().name(name).nickname(nickname).type(type).build();
        Student newStudent = studentRepository.save(student);
        // newStudent : save를 한 후 반환되는 Entity 객체

        return newStudent.getId() + newStudent.getName();
    }

    public String searchStudentByName(String name) {
        List<Student> student = studentRepository.findByName(name);
//        return "id는 " + student.getId() + "입니다.";
        return "해당하는 이름의 사용자는 " + student.size() + "명입니다.";
    }
    public String searchStudentById(int id){
        Optional<Student> a = studentRepository.findById(id);
        Student student = a.orElseThrow();
        return student.getName();
//        Student student = studentRepository.findById(id);
        // if ( student != null )
//        Student student = studentRepository
//                .findById(id)
//                .orElseThrow(() -> new RuntimeException("사용자가 없다!!!!"));
//        // orElse() : 있으면 반환하고 없으면 다른 값 반환
//        // orElseThrow() : 있으면 반환하고 없으면 error 처리
//        return student.getName();
//        Optional<Student> student = studentRepository.findById(id);
//        if ( student.isPresent() ) {
//            // ispresent : 객체의 여부를 boolean 으로 반환
//            return student.get().getName();
//            // get : Optional에 담긴 객체를 반환
//        }
//        return "null";

        // Optional<T> : java 8 부터 등장한 친구
        // null일 수도 있는 객체를 감싸는 wrapper 클래스
    }

    public int searchCntByNickname(String nickname) {
        // count 라는 method를 활용해라.
        // countByNickname(String nickname) = select count(*)
        return studentRepository.countByNickname(nickname);
    }

    public String updateStudent(int id, String name){
        // save(T) : 새로운 entity를 insert or 기존 entity를 update
        // T의 기본값(pk)의 상태에 따라 다르게 동작
        //  - pk값이 존재하는 경우 : pk와 연결된 etity를 update
        //  - pk값이 없는 경우 : 새로운 entity를 insert
        Student student = studentRepository.findById(id)
                .orElseThrow(()->new NoSuchElementException("id is wrong"));

        Student modifiedStudent =
                Student.builder()
                        .id(id).name(name).nickname(student.getNickname())
                        .type(student.getType()).build();

        studentRepository.save(modifiedStudent);
        return "Update Success";
    }
}
