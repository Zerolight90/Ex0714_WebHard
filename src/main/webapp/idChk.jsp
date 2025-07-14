<%@ page import="mybatis.vo.MemVO" %>
<%@ page import="mybatis.dao.MemberDAO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  //사용자가 입력한 아이디를 받는다.
  String mID = request.getParameter("u_id");

  if (mID != null){
    MemVO mvo = MemberDAO.chk(mID);
    int mode = 0;
    if (mvo != null){
      //HttpSession에 "mvo"라는 이름으로 MemVO객체를 저장한다.
      session.setAttribute("mvo", mvo);
      mode = 1;
    }
    response.sendRedirect("reg.jsp?mode="+mode);
  } else {
    //id가 없는 경우
    response.sendRedirect("reg.jsp");
  }

%>