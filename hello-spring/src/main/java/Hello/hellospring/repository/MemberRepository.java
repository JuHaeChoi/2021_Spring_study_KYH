package Hello.hellospring.repository;

import Hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member); //회원 정보를 저장하는 메소드
    // domain -> Member Class 참조타입

    Optional<Member> findById(Long id); // 회원 ID를 조회하는 메소드
    // <Member> 는 '제네릭스' 이다. Optional 안에 담을 수 있는 Member 클래스 참조타입 자료형만 넣을 수 있다는 뜻이다.
    // Optional 은 null 값을 대비해서 사용한다. null 이 들어갈 여지가 있다면 사용하는 것이 좋음.
    // Long Type 의 id를 넣으면 회원 ID를 조회한다.

    Optional<Member> findByName(String name); // 회원 이름을 조회하는 메소드
    //String Type 의 name 을 넣으면 회원 이름을 조회한다.

    List<Member> findAll(); //회원 전체 리스트를 조회하는 메소드

}
