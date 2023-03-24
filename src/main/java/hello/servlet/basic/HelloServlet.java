package hello.servlet.basic;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "heloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         *  http요청이 오면 servletContainer가 request, response객체를 만들어서 was 서버에 던져준다.
         *  톰캣, 제티, 언더토우 등 여러가지 was서버들이 HttpServlet의 표준스펙을 구현한다.
         */
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        // [요청] http://localhost:8080/hello?username=김 -> url에 쿼리파라미터 입력후 이동
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        // [응답] 응답메시지 전송
        response.setContentType("text/plain"); // 일반 text문자 - header정보에 데이터가 들어간다.
        response.setCharacterEncoding("utf-8"); // 문자셋 인코딩 정보 - header정보에 데이터가 들어간다.
        response.getWriter().write("hello " + username);// wrtie : http메시지바디에 데이터가 들어간다.
        
        // chrome - f12 - 네트워크 - 헤더, 페이로드 및 응답에 정보 존재
    }
}
