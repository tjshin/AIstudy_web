<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="bbs.BbsDTO" %>
<jsp:useBean class="bbs.BbsDAO" id="dao" />
<%
	int bbsno = Integer.parseInt(request.getParameter("bbsno"));
	
	dao.upViewcnt(bbsno);
	
	BbsDTO dto = dao.read(bbsno);
	
	String content = dto.getContent().replaceAll("\r\n", "<br>");
%>
 
<!DOCTYPE html> 
<html> 
<head>
  <title>게시판</title>
  <meta charset="utf-8">
  <script>
  	function update() {
  		let url = 'updateForm.jsp';
  		url += '?bbsno=<%=dto.getBbsno()%>';
  		url += "&col=<%=request.getParameter("col")%>";
  		url += "&word=<%=request.getParameter("word")%>";
  		url += "&nowPage=<%=request.getParameter("nowPage")%>";
  		
  		location.href=url;
  	}
  	function del() {
  		let url = 'deleteForm.jsp';
  		url += '?bbsno=<%=dto.getBbsno()%>';
  		url += "&col=<%=request.getParameter("col")%>";
  		url += "&word=<%=request.getParameter("word")%>";
  		url += "&nowPage=<%=request.getParameter("nowPage")%>";
  		
  		location.href=url;
  	}
  	function reply() {
  		let url = 'replyForm.jsp';
  		url += '?bbsno=<%=dto.getBbsno()%>';
  		
  		location.href=url;
  	}
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

<h2>조회</h2>

<div class="panel panel-default">
	<div class="panel-heading">작성자</div>
	<div class="panel-body"><%=dto.getWname() %></div>
	
	<div class="panel-heading">제목</div>
	<div class="panel-body"><%=dto.getTitle() %></div>
	
	<div class="panel-heading">내용</div>
	<div class="panel-body" style="height:200px"><%=content %></div>
	
	<div class="panel-heading">조회수</div>
	<div class="panel-body"><%=dto.getViewcnt() %></div>
	
	<div class="panel-heading">등록일</div>
	<div class="panel-body"><%=dto.getWdate() %></div>
</div>

<button class='btn' onclick="location.href='./createForm.jsp'">등록</button>
<button class='btn' onclick="update()">수정</button>
<button class='btn' onclick="del()">삭제</button>
<button class='btn' onclick="reply()">답변</button>
<button class='btn' onclick="list()">목록</button>
<br>
<br>

</div>
</body> 
</html>