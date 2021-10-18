<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<jsp:useBean id="dao" class="suggest.SuggestDAO" />
<%
request.setCharacterEncoding("UTF-8");
String keyword = request.getParameter("keyword");
System.out.println(keyword);
int count = dao.getCount(keyword);
List<String> list = dao.getSqlquery(keyword);
 
out.print(count + "|") ;
for(int i =0; i < list.size() ; i++){
    count = count - 1;
    String key = list.get(i);
    out.print(key);
    if (count > 0){ // 값의 중간만 콤마 출력
        out.print(",");
    }
}
%>