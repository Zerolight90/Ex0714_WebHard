<%@ page import="mybatis.vo.MemoVO" %>
<%@ page import="mybatis.dao.MemoDAO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //요청시 한글 처리
  request.setCharacterEncoding("utf-8");
  //파라미터 값들 받기
  String writer = request.getParameter("writer");
  String content = request.getParameter("content");
  String ip = request.getRemoteAddr(); //접속자 IP

  int cnt = MemoDAO.addMemo(writer, content, ip);

  //강제로 페이지 이동
  response.sendRedirect("memoList.jsp?cmd="+cnt); //" 이동할 경로 ? 인자값 "
%>


