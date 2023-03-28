package hello.servlet.web.springmvc.v2;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 통합 컨트롤러
 * 1. 매핑주소 v1 -> v2로 변경
 * 2. 전역 매핑 주소 설정
 */
@Controller
@RequestMapping("/springmvc/v2/members") //메소드레벨의 매핑주소와 조합된다.
public class SpringMemberControllerV2 {

    private MemberRepository memberRepository = MemberRepository.getInstance();


    @RequestMapping("/new-form") //요청 정보 매핑
    public ModelAndView newForm() {
        System.out.println("SpringMemberControllerV2.newForm");
        return new ModelAndView("new-form");
    }

    @RequestMapping("/save")
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("SpringMemberControllerV2.save");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);
        ModelAndView mv = new ModelAndView("save-result");
        mv.addObject("member", member);
        return mv;
    }

    @RequestMapping // 매핑주소 생략시 전역매핑으로 이동 /springmvc/v2/members
    public ModelAndView members() {
        System.out.println("SpringMemberControllerV2.members");
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
        mv.addObject("members", members);
        return mv;
    }
}
