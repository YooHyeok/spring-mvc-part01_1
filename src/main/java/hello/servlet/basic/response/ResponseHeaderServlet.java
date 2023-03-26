package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[status-line]
        response.setStatus(HttpServletResponse.SC_OK); // 200 ok

        //[response-headers]
        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setHeader("Cache-Control", "no-cache, must-revalidate"); //캐시 무효화
        response.setHeader("Pragma", "no-cache"); //과거 버전 캐시 무효화
        response.setHeader("my-header", "hello"); //내가 원하는 임의의 헤더 생성

        // [Header 편의 메서드]
        content(response);

        PrintWriter writer = response.getWriter();
        writer.println("안녕하세요");

    }

    /**
     * Header 편의 메서드
     * @param response
     */
    private void content(HttpServletResponse response) {
        //Content-Type : text/plain;charset=utf-8
        //Content-Length : 2
//        response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//        response.setContentLength(2); // 생략시 자동 생성
    }
}
