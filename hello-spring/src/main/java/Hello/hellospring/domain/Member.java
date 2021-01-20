package Hello.hellospring.domain;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Controller;

import javax.persistence.*;

@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //id 식별자, 고갱이 저장하는 것이 아닌, 시스템이 저장하는 데이터
    private String name; //회원 이름

    // Long 은 WrapperClass 이다.
    // 기본 타입의 데이터를 객체롤 표현해야 할 때가 있다.
    // 기본 타입을 객체로 다루기 위해 사용하는 클래스가 WrapperClass 이다.
    // ArrayList 에서 WrapperClass DataType 만 사용 가능하다.

    
    public Long getId() { //Id 호출
        return id;
    }

    public void setId(Long id) { //Id 세팅
        this.id = id;
    }

    public String getName() { //이름 호출
        return name;
    }

    public void setName(String name) { //이름 세팅
        this.name = name;
    }
}
