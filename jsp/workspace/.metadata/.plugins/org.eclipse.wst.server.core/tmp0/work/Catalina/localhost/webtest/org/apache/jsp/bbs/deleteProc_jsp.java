/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/9.0.50
 * Generated at: 2021-10-01 08:54:58 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.bbs;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class deleteProc_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("java.util");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    if (!javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      final java.lang.String _jspx_method = request.getMethod();
      if ("OPTIONS".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        return;
      }
      if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method)) {
        response.setHeader("Allow","GET, HEAD, POST, OPTIONS");
        response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSP들은 오직 GET, POST 또는 HEAD 메소드만을 허용합니다. Jasper는 OPTIONS 메소드 또한 허용합니다.");
        return;
      }
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write(" \r\n");
      out.write("\r\n");

	request.setCharacterEncoding("UTF-8");

      out.write('\r');
      out.write('\n');
      bbs.BbsDAO dao = null;
      dao = (bbs.BbsDAO) _jspx_page_context.getAttribute("dao", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (dao == null){
        dao = new bbs.BbsDAO();
        _jspx_page_context.setAttribute("dao", dao, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write('\r');
      out.write('\n');
      bbs.BbsDTO dto = null;
      dto = (bbs.BbsDTO) _jspx_page_context.getAttribute("dto", javax.servlet.jsp.PageContext.PAGE_SCOPE);
      if (dto == null){
        dto = new bbs.BbsDTO();
        _jspx_page_context.setAttribute("dto", dto, javax.servlet.jsp.PageContext.PAGE_SCOPE);
      }
      out.write(' ');
      out.write('\r');
      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.introspect(_jspx_page_context.findAttribute("dto"), request);
      out.write('\r');
      out.write('\n');

	Map map = new HashMap();
	map.put("bbsno", dto.getBbsno());
	map.put("passwd", dto.getPasswd());
	
	boolean pflag = dao.passCheck(map);

	boolean flag = false;
	if(pflag) {
		flag = dao.delete(dto.getBbsno());
	}

      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html> \r\n");
      out.write("<html> \r\n");
      out.write("<head>\r\n");
      out.write("  <title>게시판 삭제</title>\r\n");
      out.write("  <meta charset=\"utf-8\">\r\n");
      out.write("  <script>\r\n");
      out.write("  function list() {\r\n");
      out.write("		let url = 'list.jsp';\r\n");
      out.write("		url += \"?col=");
      out.print(request.getParameter("col"));
      out.write("\";\r\n");
      out.write("		url += \"&word=");
      out.print(request.getParameter("word"));
      out.write("\";\r\n");
      out.write("		url += \"&nowPage=");
      out.print(request.getParameter("nowPage"));
      out.write("\";\r\n");
      out.write("		\r\n");
      out.write("		location.href=url;\r\n");
      out.write("	}\r\n");
      out.write("  </script>\r\n");
      out.write("</head>\r\n");
      out.write("<body> \r\n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/menu/top.jsp", out, false);
      out.write("\r\n");
      out.write("<div class=\"container\">\r\n");
      out.write("<div class=\"well well-lg\">\r\n");
      out.write("	");

		if(!pflag) {
			out.print("잘못된 비밀번호입니다.");
		}else if(flag) {
			out.print("글 삭제 성공입니다.");
		}else{
			out.print("글 삭제 실패입니다.");
		}
	
      out.write("\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("</div>\r\n");
      out.write("	");
 if(!pflag){ 
      out.write("\r\n");
      out.write("	<button class=\"btn\" onclick=\"history.back()\">다시 시도</button>\r\n");
      out.write("	");
 } 
      out.write("\r\n");
      out.write("	<button class=\"btn\" onclick=\"location.href='createForm.jsp'\">다시 등록</button>\r\n");
      out.write("	<button class=\"btn\" onclick=\"list()\">목록</button>\r\n");
      out.write("</div>\r\n");
      out.write("</body> \r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
