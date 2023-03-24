package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        printStartLine(request);
        extracted(request);
        printHeaderUtils(request);
        printEtc(request);
    }

    /**
     * start 요청 정보
     * @param request
     */
    private static void printStartLine(HttpServletRequest request) {
        System.out.println("--- REQUEST-LINE - start ---");
        System.out.println("request.getMethod() = " + request.getMethod()); //GET
        System.out.println("request.getProtocol() = " + request.getProtocol()); //HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme()); //http
        System.out.println("request.getRequestURL() = " + request.getRequestURL());// http://localhost:8080/request-header?username=hello
        System.out.println("request.getRequestURI() = " + request.getRequestURI());// /request-test
        System.out.println("request.getQueryString() = " + request.getQueryString());//username=hello
        System.out.println("request.isSecure() = " + request.isSecure()); //https 사용 유무
        System.out.println("--- REQUEST-LINE - end ---");
        System.out.println();
    }

    /**
     * Header 모든 정보 <br/>
     * getHeaderNames() 를 호출하여 Enumeration 타입으로 반환한다. <br/>
     * Enumeration반복자를 while문으로 추출하거나 혹은 Iterator 반복자로 변환하여 forEachRemaining 함수를 호출한다. <br/>
     * forEachRemaining 함수는 함수적 인터페이스 Consumer를 매개변수로 받는 메소드를 호출하며, 내부적으로 accept까지 처리해준다. <br/>
     * @param request
     */
    private static void extracted(HttpServletRequest request) {
        System.out.println("--- Headers - start ---");

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {  // 반복자 Enumeration 문법
            String headerName = headerNames.nextElement();
            System.out.println(headerName + ": " + headerName);
        }

        request.getHeaderNames().asIterator() //Enumeration을 Iterator로 변환.
                        .forEachRemaining(headerName -> System.out.println(headerName + ": " + headerName)); // 함수적 인터페이스 Consumer를 매개변수로 받는 메소드 호출 - 내부적으로 accept 처리를 해준다.

        System.out.println("--- Headers - end ---");
        System.out.println();
    }

    /**
     * Header 편리한 조회 <br/>
     * [Host 편의 조회] : ServerName, ServerPort <br/>
     * [Accept-Language 편의 조회] : Locale <br/>
     * [cookie 편의 조회] : Cookie <br/>
     * [Content 편의 조회] : ContentType, ContentLength, CharacterEncoding <br/>
     * @param request
     */
    private static void printHeaderUtils(HttpServletRequest request) {
        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();
        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();
        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " + request.getContentType()); // contentType은 Http 본문 Body에 실려있어야 하므로 Postman으로 조작
        System.out.println("request.getContentLength() = " + request.getContentLength()); // 만약 get방식으로 조작한다면 null 혹은 -1 등의 값으로 출력된다.
        System.out.println("request.getCharacterEncoding() = " + request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }
    /**
     * 기타 정보 <br/>
     * [Remote 정보] : RemoteHost, RemoteAddr, RemotePort <br/>
     * [Local 정보] : LocalName, LocalAddr, LocalPort <br/>
     */

    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost()); //
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr()); //
        System.out.println("request.getRemotePort() = " + request.getRemotePort()); //
        System.out.println();
        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName()); //
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr()); //
        System.out.println("request.getLocalPort() = " + request.getLocalPort()); //
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }

}
