<%@ page contentType="text/html; charset=UTF-8" %> 
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="bbs.BbsDAO" id="dao"/>
<jsp:useBean class="bbs.BbsDTO" id="dto"/> 
<jsp:setProperty name="dto" property="*"/>
<%
	boolean flag = dao.create(dto);
%>

<!DOCTYPE html> 
<html> 
<head>
  <title>게시판 생성</title>
  <meta charset="utf-8">
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<div class="well well-lg">
	<%
		if(flag) {
			out.print("글 등록 성공입니다.");
		}else{
			out.print("글 등록 실패입니다.");
		}
	%>
</div>
	<button class="btn" onclick="location.href='createForm.jsp'">다시 등록</button>
	<button class="btn" onclick="location.href='list.jsp'">목록</button>
</div>
</body> 
</html>