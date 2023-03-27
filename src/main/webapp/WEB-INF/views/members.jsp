<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <!-- JSTL core c 활용 -->
    <c:forEach var="item" items="${members}">
        <tr>
            <td>id=${item.id}</td>
            <td>username=${item.username}</td>
            <td>age=${item.age}</td>
        </tr>
    </c:forEach>
    <%--
    <%
        for (Member member : members) {
            out.write("<tr>\n");
            out.write("\t<td>" + member.getId() + "</td>\n");
            out.write("\t<td>" + member.getUsername() + "</td>\n");
            out.write("\t<td>" + member.getAge() + "</td>\n");
            out.write("</tr>\n");
        }
    %>
    --%>
    </tbody>
</table>
</body>
</html>