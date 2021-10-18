<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Request Example2</h1>
프로토콜: <%=request.getProtocol() %><br>
서버의 이름: <%=request.getServerName() %><br>
서버의 포트 번호: <%=request.getServerPort() %><br>
사용자 컴퓨터의 주소: <%=request.getRemoteAddr() %><br>
사용자 컴퓨터의 이름: <%=request.getRemoteHost() %><br>
사용 method: <%=request.getMethod() %><br>
현재 사용하는 브라우저: <%=request.getHeader("User-Agent") %><br>
브라우저가 지원하는 file의 type: <%=request.getHeader("Accept") %><br>
</body>
</html>