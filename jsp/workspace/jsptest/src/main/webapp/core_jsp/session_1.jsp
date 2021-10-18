<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
    
    String season = request.getParameter("season");
    String fruit = request.getParameter("fruit");
    String id = (String)session.getAttribute("idKey");
    String sessionId = session.getId();
    int intervalTime = session.getMaxInactiveInterval();
    
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
if(id != null) {
%>
	<h1>Session Exapmle1</h1>
	<b><%=id %></b>님이 좋아하시는 계절과 과일은<p/>
	<b><%=season %></b>과 <b><%=fruit %></b>입니다.<p/>
	세션 ID: <%=sessionId %><p/>
	세션 유지 시간: <%=intervalTime %>초<p/>
<%
session.invalidate();//session에 저장된 모든 객체를 지운다.
}else{
	out.print("세션의 시간이 경과를 하였거나 다른 이유로 연결을 할 수가 없습니다.<p/>");
	//out.print("세션 ID: " + sessionId + "<p/>");
	//out.print("사용자 ID: " + id + "<p/>");
}
%>


</body>
</html>