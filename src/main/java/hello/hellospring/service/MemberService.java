package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;


// Service는 비지니스 용어에 맞게
// 테스트 케이스 단축키 : ctrl + shift + T
public class MemberService {

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();

    // Alt + Insert : 생성자 단축키
    public MemberService(MemberRepository memberRepository) {
        //MemberService 입장에서 외부에서 객체를 넣어줌 : Dependency Injection(DI)
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        validatedDuplicationMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //Extract Method : ctrl + alt + m
    private void validatedDuplicationMember(Member member) {
        memberRepository.findByName(member.getName()) //Optional을 바로 반환하는 것 보단 이렇게 하자.
                .ifPresent(m -> { // Optional이라서 가능 (m : member)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 한 명 조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
