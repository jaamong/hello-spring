package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 테스트 할 때 사용하는 어노테이션
/*
    테스트 케이스에 사용하면 테스트 시작 전에 트랙잭션을 시작하고, 테스트 완료 후에 항상 롤백. (Only for 테스트케이스)
    이렇게 하면 DB에 데이터가 남지 않으므로 다음 테스트에 영향을 주지 않는다. (-> 다음 테스트를 또 반복해서 실행할 수 있음.)
    (덕분에 굳이 @beforeeach, @aftereach 안써도 됨)
*/
@Transactional
public class MemberServiceIntegrationTest {

    /*
    테스트 케이스할 때는 그냥 편하게 field로 주입받기
    */
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void join() {
        // given : 뭔가 주어졌는데
        Member member = new Member();
        member.setName("spring");

        // when : 이거를 실행했을 때
        Long saveId = memberService.join(member);

        // then : 결과가 이게 나와야 해
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName(), is(equalTo(findMember.getName())));
    }

    @Test
    public void 중복_회원_예외() { //예외 터지는 거 확인하기
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e, is(equalTo("이미 존재하는 회원입니다.")));

    }

}
