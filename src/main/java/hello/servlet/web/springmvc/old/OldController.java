package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Controller 인터페이스를 구현한 OldController Handler
 * HandlerMapping : BeanNameUrlHandlerMapping
 * HandlerAdapter : SimpleControllerHanndlerAdapter
 */
@Component("/springmvc/old-controller") //Bean이름으로도 사용되며 URL매핑주소로도 사용된다.
public class OldController implements Controller {
    /**
     * 1. 핸들러 매핑으로 핸들러 조회
     * HandlerMapping을 순서대로 실행하여 핸들러를 찾는다.
     * 빈 이름인 /springmvc/old-controller로 핸들러를 찾아야 하기 때문에
     * 빈이름으로 핸들러를 찾아주는 BeanNameUrlHandlerMapping이 실행되고 handler인 OldController를 반환한다.
     *
     * 2. 핸들러 어댑터 조회
     * handlerAdapter의 supports()를 순서대로 호출한다.
     * SimpleControllerHandlerAdapter 가 Controller 인터페이스를 지원하므로 대상이 된다.
     *
     * 3. 핸들러 어댑터 실행
     * 디스패처 서블릿이 조회한 SimpleControllerHandlerAdapter를 실행하면서 핸들러 정보도 함께 넘겨준다.
     * SimpleControllerHandlerAdapter는 핸들러인 OldController를 내부에서 실행하고 그 결과를 반환한다.
     */
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        return null;
    }
}
