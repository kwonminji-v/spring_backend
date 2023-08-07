<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<!-- 상대경로 사용, [현재 URL이 속한 계층 경로 + /save] -->
<form action="save" method="post">
    username: <input type="text" name="username" />
    age: <input type="text" name="age" />
    <button type="submit">전송</button>

</form>
</body>
</html>

<%-- WEB-INF내부에 있는 파일들은 외부에서 직접적으로 불러와지는 것이 아니라
     무조건 컨트롤러를 통해서 뷰가 호출될 수 있도록 설정할 수 있음 (WEB-INF 안에 넣어두면 됨!)
     ex)localhost:8080/WEB-INF/views/new-form.jsp url을 작성한다고 해도 호출되지 않음 --%>