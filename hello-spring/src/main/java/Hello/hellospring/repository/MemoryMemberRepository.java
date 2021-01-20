package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMemberRepository implements MemberRepository{
// MemberRepository 상속

    private static Map<Long, Member> store = new HashMap<>(); // save 값을 저장
    private static long sequence = 0L; // 0, 1, 2... 키 값을 생성해준다.
    //Map 은 Dictionary 와 비슷하다. Map<key, value>
    //Map<string, string> 객체이름 = new Map<string, string>();
    //Map 과 HashMap 은 다형성 관계. (Map 이 더 상위개념)

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member); //Map.put(key, value) (Data 추가하기)
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable  (null 인지 아닌지 확실하지 않은 경우)
        // map.get(key 값) -> Key 에 해당하는 Value 값을 리턴.
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // 하나 찾으면 바로 나온다. 만약 끝까지 찾았는데 없으면 null 로 반환된다.

        // HashMap<Key, Value> 에서 Value 값들을 가져와서 싹 훝는다.(.Stream())
        // 입력 이름과 동일한 이름이 있는지 필터링 한다.
        // 하나라도 찾으면 Return 한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
