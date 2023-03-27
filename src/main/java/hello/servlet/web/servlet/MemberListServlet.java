package hello.servlet.web.servlet;

import hello.servlet.basic.HelloServlet;
import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * 회원 목록 폼
 */
@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HelloServlet {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");
        PrintWriter w = response.getWriter();
        List<Member> members = memberRepository.findAll();

        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");
        /*w.write("   <tr>");
        w.write("       <td>1</td>");
        w.write("       <td>userA</td>");
        w.write("       <td>10</td>");
        w.write("   </tr>");*/
        /**
         * 동적 리스트 생성
         */
        for (Member member : members) {
            w.write("   <tr>");
            w.write("   <td>" + member.getId() + "</td>");
            w.write("   <td>" + member.getUsername() + "</td>");
            w.write("   <td>" + member.getAge() + "</td>");
            w.write("   </tr>");
        }
        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");

    }
}
