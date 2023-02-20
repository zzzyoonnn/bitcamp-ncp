<%@page import="bitcamp.myapp.vo.Board"%>
<%@page import="java.util.List"%>
<%@page import="bitcamp.myapp.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
   
    
<%!
  private BoardDao boardDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    boardDao = (BoardDao) ctx.getAttribute("boardDao");
  }
%>


<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>�Խ���(JSP)</h1>

<div><a href='form.jsp'>�� ��</a></div>

<table border='1'>
<tr>
  <th>��ȣ</th> <th>����</th> <th>�ۼ���</th> <th>��ȸ��</th>
</tr>

<% 
    String keyword = request.getParameter("keyword");
    List<Board> boards = null;
    boards = this.boardDao.findAll(keyword);
    for (Board b : boards) {
%>
  <tr>
     <td><%=b.getNo()%></td> 
     <td><a href='view.jsp?no=<%=b.getNo()%>'><%=b.getTitle()%></a></td> 
     <td><%=b.getCreatedDate()%></td> 
     <td><%=b.getViewCount()%></td>
  </tr>
<% 
    }
%>
</table>

<form action='list.jsp' method='get'>
  <input type='text' name='keyword' value='<%=keyword != null ? keyword : ""%>'>
<button>�˻�</button>
</form>

</body>
</html>