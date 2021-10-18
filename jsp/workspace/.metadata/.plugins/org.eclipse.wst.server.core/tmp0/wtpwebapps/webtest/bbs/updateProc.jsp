<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ page import="java.util.*" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<jsp:useBean class="bbs.BbsDAO" id="dao"/>
<jsp:useBean class="bbs.BbsDTO" id="dto"/> 
<jsp:setProperty name="dto" property="*"/>
<%
	Map map = new HashMap();
	map.put("bbsno", dto.getBbsno());
	map.put("passwd", dto.getPasswd());
	
	boolean pflag = dao.passCheck(map);

	boolean flag = false;
	if(pflag) {
		flag = dao.update(dto);
	}
%>

<!DOCTYPE html> 
<html> 
<head>
  <title>게시판 수정</title>
  <meta charset="utf-8">
  <script>
  function list() {
		let url = 'list.jsp';
		url += "?col=<%=request.getParameter("col")%>";
		url += "&word=<%=request.getParameter("word")%>";
		url += "&nowPage=<%=request.getParameter("nowPage")%>";
		
		location.href=url;
	}
  </script>
</head>
<body> 
<jsp:include page="/menu/top.jsp"/>
<div class="container">
<div class="well well-lg">
	<%
		if(!pflag) {
			out.print("잘못된 비밀번호입니다.");
		}else if(flag) {
			out.print("글 수정 성공입니다.");
		}else{
			out.print("글 수정 실패입니다.");
		}
	%>
	
	
</div>
	<% if(!pflag){ %>
	<button class="btn" onclick="history.back()">다시 시도</button>
	<% } %>
	<button class="btn" onclick="location.href='createForm.jsp'">다시 등록</button>
	<button class="btn" onclick="list()">목록</button>
</div>
</body> 
</html>