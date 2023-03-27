package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV4", urlPatterns = "/front-controller/v4/*") // v1디렉토리 하위의 모든 url경로
public class FrontControllerServletV4 extends HttpServlet {

    private Map<String, ControllerV4> controllerMap = new HashMap<>();

    public FrontControllerServletV4() {
        controllerMap.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMap.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMap.put("/front-controller/v4/members", new MemberListControllerV4());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");
        String requestURI = request.getRequestURI(); //localhost:포트번호/ 이후의 주소
        ControllerV4 controller = controllerMap.get(requestURI);
        if (controller == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        Map<String, String> paramMap = createParamMap(request); // createParamMap : 파라미터 추출
        Map<String, Object> model = new HashMap<>();
        String viewName = controller.process(paramMap,model); // model을 넘겨받아 setAttribute에 저장할 값을 저장
        MyView view = viewResolver(viewName); // 받은 논리 이름으로 viewResolver를 통해 url주소를 생성한다.
        view.render(model, request, response); //MyView에 선언된 render 호출 => Dispacher forward 실행. model 매개변수를 넘겨받아 setAttribute에 담는 메소드 호출됨.

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
