package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { //인터페이스가 인터페이스를 받을 때는 extends

    /*
        JPA가 JpaRepository를 받고 있으면 구현체를 자동으로 만들어줌.
        스프링 빈을 자동으로 등록. 내가 하는게 아니라 Spring JpaRepository가.
        어떻게? SpringConfig
     */
    @Override
    Optional<Member> findByName(String name); //JPQL : select m from Member m where m.name = ?
}
