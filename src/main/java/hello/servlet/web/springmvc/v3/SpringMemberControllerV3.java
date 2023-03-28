package hello.servlet.web.springmvc.v3;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Spring 제공 실용적인 기능
 * 1. 매핑주소 v2 -> v3로 변경
 * 2. 각 매핑 메소드의 반환타입을 ModelAndView에서 String으로 변경
 * 3. request.getParameter 대신 @RequestParam 어노테이션 활용
 * 4. ModelAndView 대신 Model객체 활용
 * 5. RequestMapping을 Get방식 Post방식 각각의 메소드방식에 맞는 어노테이션으로 변경
 */
@Controller
@RequestMapping("/springmvc/v3/members")
public class SpringMemberControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping(value = "/new-form", method = RequestMethod.GET)
    //@GetMapping("/new-form") 으로 대체 가능
    public String newForm() {
        System.out.println("SpringMemberControllerV3.newForm");
        return "new-form";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    //@PostMapping("/save")으로 대체 가능
    public String save(Model model, @RequestParam("username") String username, @RequestParam("age") int age) {
        System.out.println("SpringMemberControllerV3.save");
        Member member = new Member(username, age);
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "save-result";
    }

    @GetMapping
    public String members(Model model) {
        System.out.println("SpringMemberControllerV3.members");
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }
}
