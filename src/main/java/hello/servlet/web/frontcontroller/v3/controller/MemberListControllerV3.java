package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.domain.member.Member;
import hello.servlet.web.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.List;
import java.util.Map;

public class MemberListControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        List<Member> members = memberRepository.findAll();
        ModelView mv = new ModelView("members");
        mv.getModel().put("members", members); //memberlist를 출력하기위해 members 리스트객체를 ModelView의 model Map객체에 담아 반환한다.
        return mv;
    }
}
