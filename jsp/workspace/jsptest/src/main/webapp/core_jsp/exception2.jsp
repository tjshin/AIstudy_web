<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    isErrorPage="true"
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

에러 메세지: <b><%=exception.getMessage() %></b><p/>
에러 실체의 클래스명과 에러 메세지: <b><%=exception.toString() %></b><p/>
</body>
</html>