package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MemberServiceTest {

    MemberService memberService; // = new MemberService();
    MemoryMemberRepository memberRepository; //= new MemoryMemberRepository(); //MemberService의 repository 객체와 다름


    @BeforeEach
    public void beforeEach() { // 테스트 실행 전에
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository); //MemberService에 넣어줌 : 같은 repository 사용
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }


    @Test
    void join() {
        // given : 뭔가 주어졌는데
        Member member = new Member();
        member.setName("hello");

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

/*      이거 때문에 try-catch문 넣기는 좀 그래서 위와 같은 방법을 쓸 수 있음
        try{
            memberService.join(member2);
            fail("예외가 발생해야 합니다.");
        } catch(IllegalStateException e) {
            assertThat(e.getMessage(), is(equalTo("이미 존재하는 회원입니다.")));
        }
*/

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}