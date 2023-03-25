package hello.servlet.basic.request;

import org.springframework.util.StreamUtils;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // messageBody 내용을 바이트 코드로 얻는다.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// Spring이 제공하는 Utility ServletInputStream 형태의 Stream 바이트코드를 String으로 변환

        response.getWriter().write(inputStream.toString() + "\n");
        response.getWriter().write(messageBody);

        /**
         * postman 
         * POST방식
         * URL : http://localhost:8080/request-body-string
         * Body - raw 선택
         * hello! 입력
         */
    }
}
