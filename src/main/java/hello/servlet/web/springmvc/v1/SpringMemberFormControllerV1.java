package hello.servlet.web.springmvc.v1;

import hello.servlet.web.frontcontroller.ModelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


/**
 * Spring RequestMapping기반 회원 등록 폼 컨트롤러
 * RequestMappingHandlerMapping은 @RequestMapping 또는 @Controller가 클래스 레벨에 붙어있는 경우 매핑정보로 인식.
 */
@Controller // 스프링의 컴포넌트 스캔 대상 및 애노테이션 기반 컨트롤러로 인식
public class SpringMemberFormControllerV1 {
    @RequestMapping("/springmvc/v1/members/new-form") //요청 정보 매핑
    public ModelAndView process() {
        return new ModelAndView("new-form");
    }
}
