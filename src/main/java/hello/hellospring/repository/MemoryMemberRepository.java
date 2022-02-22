package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

//@Repository
public class MemoryMemberRepository implements MemberRepository { // Alt + Enter : Implement method

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 세팅
        store.put(member.getId(), member); //Map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {

        // return : store.get(id)
        // 반환할 때 null일 가능성이 있으므로 Optional로 감싸기기

       return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {

        // loop 돌리기
        // 람다 사용됨 -> filter : 파라미터로 넘어온 name이 같은지 확인
        // 같은 경우에만 filtering
        // 그 중에서 그냥 찾으면(findAny() : 하나라도 찾는 것) 반환 (Optional)
        // 끝까지 찾았는데 null 반환 (Optional)

        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //store.values -> store(Map)의 Member들이 반환됨
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
