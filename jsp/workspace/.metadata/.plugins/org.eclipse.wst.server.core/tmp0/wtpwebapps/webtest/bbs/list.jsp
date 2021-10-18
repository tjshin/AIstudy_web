<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.*, bbs.*, utility.*"%>
<jsp:useBean class="bbs.BbsDAO" id="dao" />
<%//BbsDAO dao = new BbsDAO(); %>
<%//useBean과 같은 역할, 객체 생성 목적 %>
<%
//검색관련
String col = Utility.checkNull(request.getParameter("col"));
String word = Utility.checkNull(request.getParameter("word"));
if(col.equals("total")) {
	word = "";//전체 검색하면 공란
}

//paging 관련
int nowPage = 1;
if(request.getParameter("nowPage") != null) {
	nowPage = Integer.parseInt(request.getParameter("nowPage"));
}

int recordPerPage = 10;//한 페이지당 출력할 레코드
int sno = ((nowPage-1) * recordPerPage) + 1;
int eno = nowPage * recordPerPage;

Map map = new HashMap();
map.put("col", col);
map.put("word", word);
map.put("sno", sno);
map.put("eno", eno);

List < BbsDTO > list = dao.list(map);

//페이징 컴포넌트, 토털 레코드 개수를 알아야 페이지 수 정해짐
int total = dao.total(col, word);
String paging = Utility.paging(total, nowPage, recordPerPage, col, word);
%>

<!DOCTYPE html>
<html>
<head>
<title>homepage</title>
<meta charset="utf-8">

<script>
function read(bbsno) {
	let url = "read.jsp";
	url += "?bbsno=" + bbsno;
	url += "&col=<%=col%>";
	url += "&word=<%=word%>";
	url += "&nowPage=<%=nowPage%>";
	
	location.href=url;
}
</script>

</head>
<body>
	<jsp:include page="/menu/top.jsp" />
	<div class="container">
		<h1>게시판 목록</h1>
		<form class='form-inline' action='list.jsp'>
			<div class='form-group'>
				<select class="form-control" name='col'>
					<option value="wname" 
					<% if(col.equals("wname")) out.print("selected"); %>
					>성명</option>
					<option value="title" 
					<% if(col.equals("title")) out.print("selected"); %>
					>제목</option>
					<option value="content" 
					<% if(col.equals("content")) out.print("selected"); %>
					>내용</option>
					<option value="title_content" 
					<% if(col.equals("title_content")) out.print("selected"); %>
					>제목+내용</option>
					<option value='total' 
					<% if(col.equals("total")) out.print("selected"); %>
					>전체출력</option>
				</select>
			</div>
			<div class='form-group'>
				<input type='search' name="word" value="<%=word %>" class="form-control" placeholder='검색어를 입력하세요'>
			</div>
			<button class='btn'>검색</button>
			<button type='button' class='btn' onclick="location.href='createForm.jsp'">등록</button>
		</form>
		<table class='table table-striped'>

			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>grpno</th>
					<th>indent</th>
					<th>ansnum</th>
				</tr>
			</thead>

			<tbody>
				<%
				if (list.size() == 0) {
				%>
				<tr>
					<td colspan='6'>등록된 글이 없습니다.</td>
				</tr>
				<%
				} else {
				for (int i = 0; i < list.size(); i++) {
					BbsDTO dto = list.get(i);
				%>
				<tr>
					<td><%=dto.getBbsno()%></td>
					<td>
					<%
					for (int r = 0 ; r < dto.getIndent() ; r++){
						out.print("&nbsp;&nbsp;");
					}
					if(dto.getIndent() > 0) {
						out.print("<img src='../images/re.jpg'>");
					}
					%>
					<a href="javascript:read('<%=dto.getBbsno()%>')"><%=dto.getTitle()%></a>
					<% if(Utility.compareDay(dto.getWdate())) { %>
						<img src='../images/new.gif'>
					<% } %>
					</td>
					<td><%=dto.getWname()%></td>
					<td><%=dto.getGrpno()%></td>
					<td><%=dto.getIndent()%></td>
					<td><%=dto.getAnsnum()%></td>
				</tr>
				<%
				}
				}
				%>
			</tbody>

		</table>
		<%=paging %>
		<br>
		<br>
	</div>
</body>
</html>