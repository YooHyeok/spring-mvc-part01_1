package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?uername=hello&age=20
 * http://localhost:8080/request-param?uername=hello&age=20&username=kim
 */
@WebServlet(name = "requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        allParameter(request); // 전체 파라미터 조회
        singularParameter(request); // 단일 파라미터 조회
        pluralParameter(request); // 이름이 같은 복수 파라미터 조회

        response.getWriter().write("ok");
    }

    /**
     * 전체 파라미터 조회
     * @param request
     */
    private static void allParameter(HttpServletRequest request) {
        System.out.println("[전체 파라미터 조회] - start");
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println("paramName = " + request.getParameter(paramName))); //매개변수에 네임파라미터 명을 넣는다.
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();
    }

    /**
     * 단일 파라미터 조회
     * @param request
     */
    private static void singularParameter(HttpServletRequest request) {
        System.out.println("[단일 파라미터 조회] - start");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println();
    }

    /**
     * 이름이 같은 복수 파라미터 조회
     * @param request
     */
    private static void pluralParameter(HttpServletRequest request) {
        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        String[] usernames = request.getParameterValues("username");
        for (String name : usernames) {
            System.out.println("username = " + name);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();
    }
}
