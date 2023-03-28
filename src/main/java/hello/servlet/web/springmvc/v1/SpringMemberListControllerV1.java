package hello.servlet.web.springmvc.v1;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SpringMemberListControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance(); //MemberRepository가 @Reposiotry로 인식되지 않았으므로 아직은 Autowired할수없음

    @RequestMapping("/springmvc/v1/members")
    public ModelAndView process() {
        System.out.println("SpringMemberListControllerV1.process");
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("members");
//        mv.getModel().put("membesr", members); // ModelAndView의 Model은 ModelMap타입 LinkedHashMap객체이다.
        mv.addObject("members", members);
        return mv;
    }
}
