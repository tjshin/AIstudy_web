<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" buffer="5kb"%>
    <%
    int totalBuffer = out.getBufferSize();
    int remainBuffer = out.getRemaining();
    int useBuffer = totalBuffer - remainBuffer;
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>Out Example1</h1>
<b>현재 페이지의 Buffer 상태</b><br>
출력 Buffer의 전체 크기: <%=totalBuffer %>byte<br>
남은 Buffer의  크기: <%=remainBuffer %>byte<br>
현재 Buffer의 사용량: <%=useBuffer %>byte<br>
</body>
</html>