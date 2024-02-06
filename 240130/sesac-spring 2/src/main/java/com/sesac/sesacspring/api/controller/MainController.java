package com.sesac.sesacspring.api.controller;

import com.sesac.sesacspring.api.dto.UserDTO;
import com.sesac.sesacspring.api.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
// @RestController // @Controller + @ResponseBody
// ( 모든 메소드에 @ResponseBody를 붙인 것처럼 동작한다. )
public class MainController {

    // ======= Template 렌더링 =======
    @GetMapping("/")
    public String getMain(){ return "request"; }


    // ======= GET 요청 =======
    // 매개변수를 넘겨받는 방법
    // 1. /test?id=123 -> @RequestParam
    // 2. /test/123 -> @PathVariable

    @GetMapping("/get/response1")
    public String getResponse1(
            @RequestParam(value = "name") String i,
            Model model){
        /*
            @RequestParam 어노테이션
            - string query (?key=value)에서 key에 대한 value를 가져와 변수에 할당
            - ex. GET /get/response1?abc=123
            - ex. @RequestParam(value="abc") String year 은 ?abc=123에서 abc key의 값(123)을 가져와 year 변수에 해당 값을 넣어준다.
         */
        model.addAttribute("name", i);
        return "response";
    } // o
    @GetMapping("/get/response2")
    public String getResponse2(
            @RequestParam(value="name", required = false) String name,
            Model model
    ){
        /*
            @RequestParam의 required 기본값은 true
            query string에서 특정 key를 optional하게 받아야 하는 경우 required=false 설정 필요
            ex. 검색어(필수) 해시태그(선택)
                @RequestParam(value="search") String search,
                @RequestParam(value="hashtag", required=false) String hashtag
            - ?search=검색어
            - ?search=검색어&hashtag=책
         */
        model.addAttribute("name", name);
        return "response";
    } // o

    @GetMapping("/get/response3/{param1}/{param2}")
    public String getResponse3(
            @PathVariable String param1,
            @PathVariable(value = "param2") String age,
            Model model){
        /*
        * @PathVariable 어노테이션
        * - /test/{id} 형식의 URL 경로로 데이터를 넘겨줄 때 받는 방법
        * - 기본적으로 경로 변수의 값을 필수로 받아야 하기 때문 ( 보내지 않으면 404 error )
        * 참고. uri에 기입한 변수명과 다른 매개변수의 이름을 사용하고 싶다면?
        * - @PathVariable int age
        * - @PathVariable(value="age") int a
        * */
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    }

    // PathVariable을 선택적으로 처리할 시
    @GetMapping({"/get/response4/{param1}","/get/response4/{param1}/{param2}"})
    public String getResponse4(
            @PathVariable String param1,
            @PathVariable(required = false, value = "param2") String age,
            Model model) {
        /*
            1. GetMapping에 optional한 경우의 url 기입
            2. required=false 설정
                - 이때, 옵셔널한 parameter은 맨 뒤에 오도록 설정 필요
         */
        model.addAttribute("name", param1);
        model.addAttribute("age", age);
        return "response";
    } // o


    // ========== POST 요청 ==========
    // - Post 로 전달할 때 controller 에서 받는 방법은 @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value = "name") String a,
            @RequestParam(value="age") String b,
            Model model
    ){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    } // o
    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value="age", required = false) String b,
            Model model
    ){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return "response";
    } // o

    // 응답 결과를 template 호출이 아닌 데이터 자체로 응답하고 싶다면? @ResponseBody 사용
    @PostMapping("/post/response3")
    @ResponseBody
    /*
        @ResponseBody 어노테이션
        - 응답 시 객체를 json 형태로 return 할 때 사용 ( 직렬화 )
        - express의 res.send 와 유사 ( 응답 객체를 그대로 반환 )
     */
    public String postResponse3(
            @RequestParam(value = "name", required = false) String a,
            @RequestParam(value="age", required = false) String b,
            Model model
    ){
        model.addAttribute("name", a);
        model.addAttribute("age", b);
        return a + " - " + b;
    } // o



    // ========== 일반폼전송 - DTO 이용 ==========
    // - GET? O
    // - POST + ModelAttribute? O
    // - POST + RequestBody? X


    // 1. GET 요청
    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute UserDTO userDTO){
        /*
            @RequestParam으로 하나씩 값을 가져오는 것이 아닌 객체로 묶어서 가져오는 방법
            @ModelAttribute :
                - 기본적으로 실행되는 어노테이션 ( UserDTO userDTO 와 @ModelAttribute UserDTO userDTO 는 동일하게 동작 )
                - HTML 폼 데이터를 컨트롤러로 전달할 때 객체에 setter 함수를 실행하는 어노테이션
                ex. ?name=홍길동&age=10 -> setName("홍길동") setAge("10") 실행
         */
        return userDTO.getName() + " " + userDTO.getAge();
    }

    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(@RequestBody UserDTO userDTO) {
        // @RequestBody : 요청의 본문에 있는 데이터(body)를 받아 필드에 값을 직접 주입하는 어노테이션
        // Get 방식으로 전달하고 @RequstBody 로 실행 시 오류 발생
        return userDTO.getName() + " " + userDTO.getAge();
    } // x
    @GetMapping("/dto/response111")
    @ResponseBody
    public String dtoResponse111(@ModelAttribute UserDTO userDTO) {
        return userDTO.getName() + " " + userDTO.getAge();
    } // o


    // 2. POST 요청
    @PostMapping("/dto/response2")
    @ResponseBody
    public String dtoRes2(UserDTO userDTO) {
        // @ModelAttribute 어노테이션이 없을 때에는 자동 추가됨
        // 즉, 파라미터의 UserDTO 타입 앞에 아무 것도 없다면 @ModelAttribute 어노테이션이 추가됨
        return userDTO.getName() + " " + userDTO.getAge();
    } // o

    @PostMapping("/dto/response3")
    @ResponseBody
    public String dtoRes22(@RequestBody UserDTO userDTO) {
        // @RequestBody 어노테이션
        // - 요청의 본문에 있는 데이터(req.body)를 읽어와서 객체에 매핑
        // - 여기서 매핑? 필드 값에 값을 주입
        // - 전달 받은 요청의 형식이 json 또는 xml 일 때만 실행이 가능
        // POST /dto/res3 요청의 경우 "일반 폼 전송" 임 (www-x-form-urlencoded). 따라서 @RequestBody 어노테이션 사용시 오류 발생함
        return userDTO.getName() + " " + userDTO.getAge();
    } // x

    // 일반 폼전송 - DTO ( getter, setter 모두 있는 친구 )
    // 1) 어노테이션 없이 DTO로 받을 경우 -> o
    // 2) @ModelAttribute DTO 받을 경우 -> o
    // 3) @RequestBody DTO 받을 경우 -> 오류
    
    // 일반 폼 전송은 www-x-form-urlencoded 형식이기 때문에
    // get이든 post든 요청의 본문에 데이터가 들어가는 게 아닌 폼 데이터 형태로
    // url로 데이터가 전송됨. -> 즉, 일반 폼전송은 RequestBody 사용 불가



    // ========== 일반폼전송 -  VO 이용 ==========
    //- GET? NULL
    //- POST + ModelAttribute? Null
    //- POST + RequestBody? X

    // 1. GET 요청
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO){
        // @ModelAttribute 를 이용하면 객체의 set 함수를 이용해 값을 넣어줌
        return userVO.getName() + " " + userVO.getAge();
    } // o(null)
    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO) {
        return userVO.getName() + " " + userVO.getAge();
    } // o(null)
    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO){
        return userVO.getName() + " " + userVO.getAge();
    } // x



    // ========== DTO 이용 with. axios ==========
    // - GET RequestParam : o
    //- GET ModelAttribute : o
    //- GET RequestBody : x
    //- POST RequestParam : x
    //- POST ModelAttribute : null

    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age){
        return name + " " + age;
    } // o
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDTO userDTO){
        // @ModelAttribute
        // axios = application/json
        return userDTO.getName() + " " + userDTO.getAge();
    } // o

    @PostMapping("/axios/response3")
    @ResponseBody
    // @RequstParam required 기본값이 true
    // axios로 값을 전달하게 될 경우 파라미터로 값이 들어오지 않는다.(Post로 보냈을 때)
    // 값이 들어오지 않는데, @RequestParasm의 required가 기본값이 true이기 때문에 오류
    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: "+ age;
    } // x
    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosRes4(UserDTO userDTO){
        // Axios로 post를 보낼 경우 본문에 데이터가 들어가기에 @ModelAttribute가 확인 불가 = null
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    } // o(null)
    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosRes5(@RequestBody UserDTO userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    } // o



    // ========== VO 이용 with. axios ==========
    // - GET RequestParam : o
    //- GET ModelAttribute : NULL
    //- GET RequestBody : x
    //- POST RequestParam : x
    //- POST ModelAttribute : null
    //- POST RequestBody : o

    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    } // o
    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoRes2(UserVO userVO) {
        // @ModelAttribute로 값이 들어갈 때는 setter 함수를 실행해서 값을 넣어주기 때문에
        // setter 함수가 없는 UserVO에는 값이 들어갈 수 없다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    } // o(null)
    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    } // x
    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoRes4(UserVO userVO){
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    } // o(null)
    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVO userVO){
        // @RequestBody로 값을 전달할때 userVO에 setter 함수가 없어도 값이 들어간다.
        // @RequestBody는 setter 함수 실행이 아니라 각각의 필드(변수)에 직접적으로 값을 주입하면서 매핑
        // @ModelAttribute가 setter 함수를 실행해 값을 넣어준다면
        // @RequestBody는 각각의 필드(변수)에 직접적으로 값을 주입한다. 필드에 내장된 set 함수를 실행
        // 즉, @RequestBody는 UserVO UsrDTO 상관없이 setter 메소드의 유무와 관계없이 변수에 값을 넣을 수 있다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    } // o
}
