package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {

    /*
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    // MemberRepository 와 MemoryMemberRepository 는 다형성 관계
    */
    private final MemberRepository memberRepository;

   @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    } //직접 생성하는 것이 아니라 외부에서 넣어주도록 한다. (Test 와 다른 객체가 되기 때문)
    //생성자 주입


    //회원가입
    public Long join(Member member){
        //같은 이름을 가진 중복회원을 방지

        /* A)
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        위의 코드를 아래와 같이 줄일 수 있다.*/

        validateDuplicateMember(member); //중복회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    //위의 A) 코드를 줄이고, 메소드로 추출함.
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //회원 한명 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}