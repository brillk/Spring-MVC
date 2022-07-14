package Hi.servlet.web.springmvc.v3;

import Hi.servlet.domain.member.Member;
import Hi.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    // 미친 V2랑 비교도 안되네 뭐임?

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @GetMapping("/new-form") // GET인 경우에만 호출된다, 제약을 걸어
    public String newForm() {
        return "new-form";
    }

    @PostMapping("/save")
    public String save(
            @RequestParam("username") String username,
            @RequestParam("age") int age,
            Model model
        ) {

        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result"; // 뷰의 이름 반환
    }

    @GetMapping
    public String members(Model model) {

        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
