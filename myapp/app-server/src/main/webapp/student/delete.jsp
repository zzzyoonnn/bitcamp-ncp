<%@page import="bitcamp.myapp.dao.MemberDao"%>
<%@page import="bitcamp.util.TransactionManager"%>
<%@page import="bitcamp.myapp.vo.Student"%>
<%@page import="java.util.List"%>
<%@page import="bitcamp.myapp.dao.StudentDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">

<%!
  private TransactionManager txManager;
  private MemberDao memberDao;
  private StudentDao studentDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    txManager = (TransactionManager) ctx.getAttribute("txManager");
    memberDao = (MemberDao) ctx.getAttribute("memberDao");
    studentDao = (StudentDao) ctx.getAttribute("studentDao");
  }
  
  private static String getLevelText(int level) {
    switch (level) {
      case 0: return "��������";
      case 1: return "��������";
      default: return "������";
    }
  }
%>

<% 
    int studentNo = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>�л�(JSP)</h1>

<% 
    txManager.startTransaction();
    try {
      if (studentDao.delete(studentNo) == 1 &&
          memberDao.delete(studentNo) == 1) {
        txManager.commit();
%>

    <p>�����߽��ϴ�.</p>

<% 
      } else {
%>
    <p>�ش� ��ȣ�� �л��� �����ϴ�.</p>
    
<% 
      }
    } catch (Exception e) {
      txManager.rollback();
%>
  <p>���� �����Դϴ�.</p>
  
<% 
      e.printStackTrace();
    }
%>

</body>
</html>

<% 
    response.setHeader("Refresh", "1;url=list.jsp");
%>
