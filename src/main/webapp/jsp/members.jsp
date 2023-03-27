<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    System.out.println("membersJsp.service");

    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
    <th>id</th>
    <th>username</th>
    <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("<tr>\n");
            out.write("\t<td>" + member.getId() + "</td>\n");
            out.write("\t<td>" + member.getUsername() + "</td>\n");
            out.write("\t<td>" + member.getAge() + "</td>\n");
            out.write("</tr>\n");
        }
    %>
    </tbody>
</table>
</body>
</html>