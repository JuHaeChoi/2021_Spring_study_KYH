package Hello.hellospring.controller;

import Hello.hellospring.domain.Member;
import Hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller //스프링 컨테이너생성시 컨트롤러 어노테이션이 있으면 인식하고 MemberController 객체를 생성하고 스프링에 넣어둔다. + 스프링이 관리
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // 객체를 하나만 만들어서 여러곳에서 공유하는 것이 더 효율적이다.

    private final MemberService memberService;
    //스프링 컨테이너에 등록, 생성자로 연결

    //스프링 컨테이너가 생성될 때 멤버 컨트롤러를 생성하고 생성자를 호출하는데 생성자에 @Autowired 가 붙어있으면.
    //멤버 서비스를 스프링이 스프링컨테이너에 있는 멤버 서비스를 가져다가 연결을 시켜준다.
    //즉, 멤버 컨트룰러가 생성이 될 때, 스프링 빈에 등록되어 있는 멤버 서비스 객체를 가져와 넣어준다. 이것이 DI(의존관계 주입) 이다.

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }//생성자 주입

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
