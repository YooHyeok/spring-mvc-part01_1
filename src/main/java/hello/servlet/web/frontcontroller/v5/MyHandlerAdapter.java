package hello.servlet.web.frontcontroller.v5;

import hello.servlet.web.frontcontroller.ModelView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface MyHandlerAdapter {
    boolean supports(Object handler); // 컨트롤러가 넘어왔을 때 Handler 지원 여부

    ModelView handle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
