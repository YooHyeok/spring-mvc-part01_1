package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.ControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*") // v1디렉토리 하위의 모든 url경로
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");
        String requestURI = request.getRequestURI(); //localhost:포트번호/ 이후의 주소
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request); // createParamMap : 파라미터 추출
        ModelView mv = controller.process(paramMap); //ModelView 생성자를 통해 url 논리 이름 필드인 viewName이 초기화 된다
        String viewName = mv.getViewName(); // 논리 이름을 반환한다.
        MyView view = viewResolver(viewName); // 받은 논리 이름으로 viewResolver를 통해 url주소를 생성한다.
        view.render(mv.getModel(), request, response); //MyView에 선언된 render 호출 => Dispacher forward 실행. / mv.getModel() 매개변수에 의해 model에 담는 메소드 호출됨.

    }

    /**
     * 매개변수를 request와 response대신 paramMap이라는 Map객체로넘겨야하므로 request객체로부터 파라미터를 추출한다
     * @param viewName
     * @return
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    /**
     * Parameter를 추출해서 Model역할을 해주는 ModelView클래스의 Map타입의 paramMap 필드에 순차적으로 put하여 초기화 저장한다.
     * @param request
     * @return ModelView의 Map필드
     */
    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
