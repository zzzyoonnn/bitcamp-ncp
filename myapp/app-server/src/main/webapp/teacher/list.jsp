<%@page import="bitcamp.myapp.vo.Teacher"%>
<%@page import="java.util.List"%>
<%@page import="bitcamp.myapp.dao.TeacherDao"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%! 
 private TeacherDao teacherDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
    teacherDao = (TeacherDao) ctx.getAttribute("teacherDao");
  }
  
  private static String getDegreeText(int degree) {
    switch (degree) {
      case 1: return "����";
      case 2: return "�����л�";
      case 3: return "�л�";
      case 4: return "����";
      case 5: return "�ڻ�";
      default: return "��Ÿ";
    }
  }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>����(JSP)</h1>

<div><a href='form.jsp'>�� ����</a></div>

<table border='1'>
<tr>
  <th>��ȣ</th>
  <th>�̸�</th>
  <th>��ȭ</th>
  <th>����</th>
  <th>����</th>
  <th>�ð���</th>
</tr>

<% 
    List<Teacher> teachers = this.teacherDao.findAll();
    for (Teacher teacher : teachers) {
%>

  <tr>
      <td><%=teacher.getNo()%></td> 
      <td><a href='view.jsp?no=<%=teacher.getNo()%>'><%=teacher.getName()%></a></td> 
      <td><%=teacher.getTel()%></td> 
      <td><%=getDegreeText(teacher.getDegree())%></td> 
      <td><%=teacher.getMajor()%></td> 
      <td><%=teacher.getWage()%></td>
  </tr>
<%
    }
%>
</table>

</body>
</html>

