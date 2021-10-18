<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%!public int tot(int kuk, int eng, int mat) {
		return kuk + eng + mat;
	}

	public int avg(int tot) {
		return tot / 3;
	}%>

	<%
	String name = "아로미";
	int kuk = 90;
	int eng = 85;
	int mat = 100;
	int tot = tot(kuk, eng, mat);
	int avg = avg(tot);
	%>
	<h1>성적표</h1>
	<ul style='font-size: 24px;'>
		<li>성명: <%=name%>
		<li>국어: <%=kuk%>
		<li>영어: <%=eng%>
		<li>수학: <%=mat%>
		<li>총점: <%=tot%>
		<li>평균: <%=avg%>
	</ul>

</body>
</html>