<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String name = "왕눈이";
	int kuk = 90;
	int eng = 95;
	int tot = kuk + eng;
	int avg = tot / 2;
	%>
	
	<h1> 성적표 </h1>
	<div style = 'font-size: 24px;'>
	----------------------------<br>
	성명: <% out.println(name); %><br>
	국어: <% out.println(kuk); %><br>
	영어: <% out.println(eng); %><br>
	총점: <%=tot %><br>
	평균: <%=avg %><br>
	</div>
	
	<%
    	System.out.println(">>>> name:" + name);
	%>
</body>
</html>