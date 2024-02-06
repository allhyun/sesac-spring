package com.sesac.sesacspring.controller;

import com.sesac.sesacspring.DTO.UserDto;
import com.sesac.sesacspring.vo.UserVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@RestController //@Controller +@ responsebody
public class MainController {
    @GetMapping("/")
    public String getMain(){ return "request"; }



    // Get=====
    // 매개변수를 넘겨받는 방법
    //1. /test?id=123 -> @RequestParam 쿼리로 넘겨받기
    //2. /test/123  ->@PathVariable
    @GetMapping("/get/response1")
    // ?key=value
    // ?name=
    // @RequestParam 는 기본값으로 required=true
    public String getResponse1(
            @RequestParam(value = "name") String i,
            Model model){
        //@RequestParam 어노테이션
        // - ?name=112&id=11&age=abc(ㅇ)
        //-?id=11&hashtag=abc (x)
        // - string query(?뒤의 값)에서 key("name")에 대한 value("112")를 변수 "i" 에 매핑
        // -required=true 기본값 -< 요청 url에서 설정한 key 가 필수로 있어야 한다.
        model.addAttribute("name", i);
        return "response";
    }

    //
    //?search=검색어&hashtag=코딩
    @GetMapping("/get/response2")
    //required=false 옵션(@RequestParam(value="",required=false))
    //-query string 에서 특정 key를 옵셔널하게 받아야 하는 경우
    //ex>검샐할때 (검색어(필수) 해시태드(선택)
    //@RequestPama(value="serach")String search,
    //@RequestParam(value="hashtag", required=false)String hashtag
    public String getResponse2(
            @RequestParam(value="name", required = false) String name,
            Model model
    ){
        model.addAttribute("name", name);
        return "response";
    }


    @GetMapping("/get/response3/{param1}/{param2}")
    //url   안에 넣을떄 @PathVatiable
    public String getResponse3(@PathVariable String param1,
//                               @PathVariable String param2,
                               @PathVariable(value = "param2") String age,
                               Model model){

        /*
        * @PathVariable 어노테이션
        * - /test/{id} 형식의 URL 경로로 데이터를 넘겨줄 떄 받는 방법
        * - 기본적으로 경로 변수의 값을 필수로 받아야하기 때문 (보내지 않으면 404error 발생)
        *
        * */
        model.addAttribute("name",param1);
        model.addAttribute("age",age);
        return "response";
    }

    @GetMapping({"/get/response4/{param1}","/get/response4/{param1}/{param2}"})
    //url   안에 넣을떄 @PathVatiable
    public String getResponse4(
            @PathVariable String param1,
            @PathVariable(required = false,value="param2") String age,
            Model model){

        //중요 ! optional한 parameter은 맨 뒤에 오도록 설정
        model.addAttribute("name",param1);
        model.addAttribute("age",age);
        return "response";
    }

    @PostMapping("/post/response1")
    public String postResponse1(
            @RequestParam(value="name", required = true) String a,
            @RequestParam(value="age", required = true) String b,
            Model model
    ){
        model.addAttribute("name",a);
        model.addAttribute("age",b);
        return "response";
    }

    @PostMapping("/post/response2")
    public String postResponse2(
            @RequestParam(value="name", required = false) String a,
            @RequestParam(value="age", required = false) String b,
            Model model
    ){
        model.addAttribute("name",a);
        model.addAttribute("age",b);
        return "response";
    }

    //@ResponseBody란?
    //응답을 할때 그 객체를 씨리얼라이즈..?해서 보낸다? _ 객체를 직렬화해서 보낸다 _ 응답시 객체를 json형태로 보낸다
    //express res.send와 유사하다

    @PostMapping("/post/response3")
    @ResponseBody
    public String postResponse3(
            @RequestParam(value="name", required = false) String a,
            @RequestParam(value="age", required = false) String b,
            Model model
    ){
        model.addAttribute("name",a);
        model.addAttribute("age",b);
//        return "response";
        return a + "- " + b;
    }



    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute UserDto userDTO){
        //DTO : getter와 setter가 있는 객체
        //GEt 방식에서 dto객체로 담아서 값이 받아지는구나
        //ModelAttribute : HTML 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑
        // 매핑 = setter함수 실행
        //?name=홍길동&age=10 -> setName("홍길동") setAge("10)
        return userDTO.getName()+" "+userDTO.getAge();
    }

    //@RequestBody : 요청의 본문에 있는 데이터 (body)를 받는 친구
    @GetMapping("/dto/response11")
    @ResponseBody
    public String dtoResponse11(UserDto userDto){
        return userDto.getName()+" "+ userDto.getAge();
    }

    //form 파일 업로드 할때 <form enctype>
    //일반 폼 전송 -> requestbody
    //RequeestBody 는 요청의 본문에 있는 데이터 (body)를 처리할 수 있기 떄문에
    //json,xml 일떄만 실행이 가능
    //application/json

    //일반 폼 전송 -DTO  (getter,setter 모두 있는 친구)
    //1) 어노테이션 없이 DTO로 받을경우 -> ㅇ
    //2) @ModelAttribute DTO받을 경우 ->ㅇ
    //3) @RequestBdoy DTO 받을 경우 -> 오류

    //일반 폼 전송은 www-x-form-urlencoded 형식이기 떄문에
    //get이든 post든 요청의 본문에 데이터가 들어가는게 아닌 폼 데이터 형태로
    //url로 데이터가 전송됨 -> 즉, 일반 폼 전송을 RequestBody 사용불가


    //일반 폼전송 -VO

    //get @modelattribute => null null
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVO userVO){
        return userVO.getName()+" "+userVO.getAge();
    }

    //post @modelattribute => null null
    @PostMapping("/vo/response2")
    @ResponseBody
    public String voResponse2(UserVO userVO){
        return userVO.getName()+" "+userVO.getAge();
    }

    //post @RequestBody => 오류
    @PostMapping("/vo/response3")
    @ResponseBody
    public String voResponse3(@RequestBody UserVO userVO){
        return userVO.getName()+" "+userVO.getAge();
    }


    //////////// -- axios

    //1.Axios -get - @RequestParam -> o
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosResponse1(@RequestParam String name, @RequestParam String age){
        return name + " " + age;
    }

    //2.Axios -get - @RModelAttribute -> o
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosResponse2(UserDto userDto){

        //@modelattribute
        //axios = application/json
        return userDto.getName()+" "+ userDto.getAge();
    }

    @PostMapping("/axios/response3")
    @ResponseBody

    //url이었는데 axios post는 url에 데이터가 x
    //url에 아무것도 없는데 name, age required=true이기 때문에 에러가 발생

    public String axiosRes3(@RequestParam String name, @RequestParam String age){
        return "이름: " + name + ", 나이: "+ age;
    }

    @PostMapping("/axios/response4")
    @ResponseBody

    //ModelAttribute를 이용해 데이터를 보냈을 떄 값이 널
    // 악시오스로 보내면 유알엘로 데이터를 보내는게아니라 본문으로 데이러를 보내게된다
    // 즉 모델 어트리뷰트가 값을 볼 수가 없음
    public String axiosRes4(UserDto userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    }

    @PostMapping("/axios/response5")
    @ResponseBody
    //

    public String axiosRes5(@RequestBody UserDto userDTO){
        return "이름:" + userDTO.getName() + ", 나이: "+ userDTO.getAge();
    }





    // ========== VO 이용 with. axios ==========
    @GetMapping("/axios/vo/response1")
    @ResponseBody
    public String axiosVoRes1(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @GetMapping("/axios/vo/response2")
    @ResponseBody
    public String axiosVoRes2(UserVO userVO) {
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response3")
    @ResponseBody
    public String axiosVoRes3(@RequestParam String name, @RequestParam String age) {
        return "이름: " + name + ", 나이: " + age;
    }

    @PostMapping("/axios/vo/response4")
    @ResponseBody
    public String axiosVoRes4(UserVO userVO){
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }

    @PostMapping("/axios/vo/response5")
    @ResponseBody
    public String axiosVoRes5(@RequestBody UserVO userVO){
        //axios post로 데이터를 보내면 요청의 본문(body)에 데이터가 들어간다.
        //@RequsetBody는 요청의 본문에 있는 데이터를 읽을 수 있다.
        //UserVO 클래스는 setter메소드가 없어요
        //@RequstBody 는 데이터를 각ㄱ가의 필드(변수)에 직접적으로 값 주입
        //@RequestBody는
        //UserVO UserDto상관없이 Setter 메소드의 유무와 관계없이 변수에 값을 넣을 수 있다.
        return "이름: "+ userVO.getName() + ", 나이: "+ userVO.getAge();
    }


}
