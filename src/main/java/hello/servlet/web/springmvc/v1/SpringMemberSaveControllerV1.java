package hello.servlet.web.springmvc.v1;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class SpringMemberSaveControllerV1 {
    private MemberRepository memberRepository = MemberRepository.getInstance(); //MemberRepository가 @Reposiotry로 인식되지 않았으므로 아직은 Autowired할수없음

    @RequestMapping("/springmvc/v1/members/save")
    public ModelAndView process(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("SpringMemberSaveControllerV1.process");
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        Member member = new Member(username, age);
        memberRepository.save(member);

        ModelAndView mv = new ModelAndView("save-result");
//        mv.getModel().put("member", member); // ModelAndView의 Model은 ModelMap타입 LinkedHashMap객체이다.
        mv.addObject("member", member);
        return mv;
    }
}
