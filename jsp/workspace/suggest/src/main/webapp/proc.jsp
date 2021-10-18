<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>Insert title here</title>
</head>
<body>
	<h2>
		<%
		request.setCharacterEncoding("UTF-8");

		String keyword = request.getParameter("keyword");

		out.println("keyword:" + keyword);
		%>
	</h2>
</body>
</html>