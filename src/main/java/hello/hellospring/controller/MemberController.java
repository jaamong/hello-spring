package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller //스프링 컨테이너가 관리
public class MemberController {

    private final MemberService memberService;// = new MemberService();

    //Controller랑 Service랑 연결 : 생성자에 @Autowired
    @Autowired // -> MemberController가 생성될 때 스프링 빈(Bean)에 등록되어 있는 MemberService 객체(@Service)를 가져다가 넣어줌(DI : Dependency Injection)
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/"; //회원가입 후 home화면으로 리다이렉트
    }
}
