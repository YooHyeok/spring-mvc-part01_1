<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Title</title>
</head>
<!-- WEB-INF 디렉토리 하위의 자원들은 외부에서 호출되지 않는다. 이것은 WAS에서 정해진 룰이다.
    기본적으로 webapp 디렉토리 하위에 있는 index.html파일은 [localohst:8080/home.html]로 열린다.
    하지만 webapp 디렉토리 하위의 WEB-INF/views/index.html파일을 [localhost:8080/WEB-INF/views/home.html]로 입력하면 열리지 않는다.
    -->
<body>
<!-- form action 상대경로 사용, [현재 URL이 속한 계층 경로 + /save]
      [/save] : 절대경로 - localhost:8080/save 로 이동
      [save] : 상대경로 - localhost:8080/servlet-mvc/members/save 로 이동 -->
<form action="save" method="post">
  username: <input type="text" name="username"/>
  age: <input type="text" name="age"/>
  <button type="submit">전송</button>
</form>
</body>
</html>
