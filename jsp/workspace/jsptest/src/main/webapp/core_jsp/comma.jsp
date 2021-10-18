<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.text.DecimalFormat" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%!public String comma(long val) {
	DecimalFormat df = new DecimalFormat("￦ ###,###,### 원");
	String str = df.format(val);
	
	return str;
	}
	%>
	
	<h1>9월 급여 명세서</h1>
	<div style='font-size: 24px; color: #FFFFFF; background-color: #000055'>
	본봉: <%=comma(1800000) %><br>
	수당: <%=comma(200000) %><br>
	세금:<span style='color:#FF0000'><%=comma(100000) %></span><br>
	실수령액: <%=comma(1900000) %><br>
	<br> <span style='color: #00FF00'>수고하셨습니다.</span>
	
	</div>

</body>
</html>