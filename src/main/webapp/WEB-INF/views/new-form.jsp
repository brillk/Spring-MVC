<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save]

/WEB-INF
> 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없다.
우리가 기대하는 것은 항상 컨트롤러를 통해서 JSP를 호출하는 것이다
-->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>
</form>
</body>
</html>