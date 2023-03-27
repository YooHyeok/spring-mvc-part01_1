package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;
        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

        String viewName = controller.process(paramMap, model);
        ModelView mv = new ModelView(viewName);
        /**
         * 기존 v3 코드에서는 ModelView mv = controller.process(paramMap);
         * 과 같이 ModelView타입으로 반환했지만, v4 코드에서는 String타입으로 다음과 같이 변경된다.
         * String viewName = controller.process(paramMap, model);
         * 이것에 대한 변환은 new 인스턴스 생성자를 통해 viewName 필드를 초기화 하면서 객체를 반환받으면 해결된다.
         * v3에서는 process 내부에서 viewName을 세팅하고, mv.getModel().put("member",member);를 통해 model객체를 반환한다.
         * v4에서는 process에 model을 직접 넘겨 model.put을 한 뒤, viewName을 반환한다.
         * 따라서 Model을 어디서 선언하고 어디서 초기화하는지에 따라 viewName의 초기화 위치가 달라지는 차이를 확인할 수 있다.
         */
        mv.setModel(model);
        return mv;
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
