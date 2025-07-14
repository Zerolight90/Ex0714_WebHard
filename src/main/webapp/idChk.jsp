<%@ page import="mybatis.vo.MemVO" %>
<%@ page import="mybatis.dao.MemberDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //사용자가 입력한 아이디를 받는다.
  String mID = request.getParameter("u_id");

  if(MemberDAO.chk(mID))
    response.sendRedirect("reg.jsp?m_id="+mID+"&chk=1");
  else
    response.sendRedirect("reg.jsp?m_id="+mID+"&chk=0");

%>