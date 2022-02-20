package hello.hellospring.reposiotory;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


// 외부에서 부를 일 없으니까 굳이 public으로 안해도 됨
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 중요!!
    @AfterEach
    public void afterEach() {
        //하나의 테스트가 끝날 때 마다 저장소나 공용 데이터를 비워야 문제가 없음
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        // 검증 방법 1 : 그냥 프린트 해보기, 똑같으면 true 출력
        // System.out.println("result = " + (result == member));

        // 검증 방법 2 : result랑 member가 똑같은지 -> 같으면 초록불, 다르면 빨간불
        // Assertions.assertEquals(member, result);

        // 검증 방법 3 : isEqualTo
        assertThat(member, is(equalTo(result)));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result, is(equalTo(member1)));
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size(), is(equalTo(result.size())));
    }
}
