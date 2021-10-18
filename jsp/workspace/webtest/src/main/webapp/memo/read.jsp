<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="memo.MemoDTO" %>
<jsp:useBean class="memo.MemoDAO" id="dao" />
<%
	int memono = Integer.parseInt(request.getParameter("memono"));
	
	dao.upViewcnt(memono);
	
	MemoDTO dto = dao.read(memono);
	
	String content = dto.getContent().replaceAll("\r\n", "<br>");
%>
 
<!DOCTYPE html> 
<html> 
<head>
  <title>메모</title>
  <meta charset="utf-8">
  <script>
  	function update() {
  		let url = 'updateForm.jsp';
  		url += '?memono=<%=dto.getMemono()%>';
  		url += "&col=<%=request.getParameter("col")%>";
  		url += "&word=<%=request.getParameter("word")%>";
  		url += "&nowPage=<%=request.getParameter("nowPage")%>";
  		
  		location.href=url;
  	}
  	function del() {
  		let url = 'deleteForm.jsp';
  		url += '?memono=<%=dto.getMemono()%>';
  		url += "&col=<%=request.getParameter("col")%>";
  		url += "&word=<%=request.getParameter("word")%>";
  		url += "&nowPage=<%=request.getParameter("nowPage")%>";
  		
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
<button class='btn' onclick="list()">목록</button>
<br>
<br>

</div>
</body> 
</html>