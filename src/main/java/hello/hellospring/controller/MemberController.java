package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //스프링 컨테이너가 관리
public class MemberController {

    private final MemberService memberService;// = new MemberService();

    //Controller랑 Service랑 연결 : 생성자에 @Autowired
    @Autowired // -> MemberController가 생성될 때 스프링 빈(Bean)에 등록되어 있는 MemberService 객체(@Service)를 가져다가 넣어줌(DI : Dependency Injection)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
}
