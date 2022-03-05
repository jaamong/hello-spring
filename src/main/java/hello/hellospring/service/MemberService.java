package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
    JPA를 쓰려면 Transaction이 필요함 -> Service에 추가(회원가입에만 필요해서 거기에 해둬도 되는데.. 일단 여기에)
    JPA는 Join 들어올 때 모든 데이터 변경이 Transaction 안에서 실행되어야 함.
 */
@Transactional
public class MemberService {

    private final MemberRepository memberRepository; //= new MemoryMemberRepository();

    @Autowired
    public MemberService(MemberRepository memberRepository) { // Alt + Insert : 생성자 단축키
        //MemberService 입장에서 외부에서 객체를 넣어줌 : Dependency Injection(DI)
        this.memberRepository = memberRepository;
    }


    /**
     * 회원 가입
     * Validation : 같은 이름이 있는 중복 회원 X
     */
    public Long join(Member member) {

        //메소드 호출 시간 측정
        long start = System.currentTimeMillis();

        try {
            validatedDuplicationMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
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
