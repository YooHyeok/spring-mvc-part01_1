package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("MvcMemberFormServlet.service");
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//controller에서 view로 이동할때 사용.
        dispatcher.forward(request,response); // jsp를 찾아서 호출한다.
        /**
         * [RequestDispatcher - forward]
         * RequestDispatcher는 클라이언트로부터 최초에 들어온 요청을 JSP/Servlet 내에서 원하는 자원으로 요청을 넘기는(보내는) 역할을 수행하거나,
         * 특정 자원에 처리를 요청하고 처리 결과를 얻어오는 기능을 수행하는 인터페이스이다.
         * RequestDispatcher의 forward() 메서드는 대상 자원으로 제어를 넘기는 역할을 한다.
         * 브라우저에서 url이 /a인 서블릿으로 요청했을 때 서블릿에서 forward("/b")를 호출하면
         * 브라우저 입장에서는 /a를 요청했지만 받은 결과는 /b의 결과를 받게 된다.
         * 이때 HTTP 리다이렉트 방식과는 달리 하나의 HTTP 요청(Request) 범위 안에서 동작이 이루어진다.
         *
         *          -> /a 요청 ->
         * 브라우저             서버
         *          <- /b 응답 <-
         * -------------------------------------------------------------------------------------------------------------------
         * [HttpServletResponse - Redirect]
         * HttpServletResponse를 사용하면 sendRedirect() 메서드를 이용하여 지정한 경로로 제어를 이동시킬 수 있다.
         * 그러나 sendRedirect()는 HTTP 리다이렉션을 이용하기 때문에 하나의 요청 범위 안에서 처리를 하는것이 아니라
         * 브라우저에게 Response 후 브라우저측에서 지정받은 요청 경로로 다시 재요청을 하는 방식이기에 두 번의 HTTP 트랜잭션이 발생하며,
         * 서버측에서는 최초에 받은 요청중에 처리한 내용을 리다이렉트 된 요청안에서 공유할 수 없는 문제가 있다.
         *
         *          -> /a 요청 ->
         *          <- /b 응답 <-
         * 브라우저             서버
         *          -> /b 요청 ->
         * -------------------------------------------------------------------------------------------------------------------
         * [Redirect vs Forward]
         * 리다이렉트는 실제 클라이언트(웹 브라우저)에 응답이 나갔다가, 클라이언트가 redirect경로로 다시 요청한다.
         * 따라서 클라이언트가 인지할 수 있다 (페이지 깜.박)
         * 반면 포워드는 서버 내부에서 일어나는 호출이기 때문에 클라이언트가 전혀 인지하지 못한다.
         */
    }
}
