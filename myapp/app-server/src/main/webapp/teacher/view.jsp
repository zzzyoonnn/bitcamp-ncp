<%@page import="bitcamp.myapp.dao.MemberDao"%>
<%@page import="bitcamp.util.TransactionManager"%>
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

<% 
    int teacherNo = Integer.parseInt(request.getParameter("no"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>����(JSP)</h1>

<% 
    Teacher teacher = this.teacherDao.findByNo(teacherNo);

    if (teacher == null) {
%>
    	
  <p>�ش� ��ȣ�� ���簡 �����ϴ�.</p>

<% 
    } else {
%>

  <form id='teacher-form' action='update' method='post'>

  <table border='1'>

  <tr>
    <th>��ȣ</th>
      <td><input type='text' name='no' value='<%=teacher.getNo()%>'></td>
  </tr>

  <tr>
    <th>�̸�</th>
      <td><input type='text' name='name' value='<%=teacher.getName()%>'></td>
  </tr>

  <tr>
    <th>�̸���</th>
      <td><input type='email' name='email' value='<%=teacher.getEmail()%>'></td>
  </tr>

  <tr>
    <th>��ȣ</th>
    <td><input type='password' name='password'></td>
  </tr>

  <tr>
    <th>��ȭ</th>
      <td><input type='tel' name='tel' value='<%=teacher.getTel()%>'></td>
  </tr>

  <tr>
    <th>����</th>
      <td><select name='degree'>
        <option value='1' <%=teacher.getDegree() == 1 ? "selected" : ""%>>����</option>
        <option value='2' <%=teacher.getDegree() == 2 ? "selected" : ""%>>�����л�</option>
        <option value='3' <%=teacher.getDegree() == 3 ? "selected" : ""%>>�л�</option>
        <option value='4' <%=teacher.getDegree() == 4 ? "selected" : ""%>>����</option>
        <option value='5' <%=teacher.getDegree() == 5 ? "selected" : ""%>>�ڻ�</option>
        <option value='0' <%=teacher.getDegree() == 0 ? "selected" : ""%>>��Ÿ</option>
      </select></td>
  </tr>

  <tr>
    <th>�б�</th>
      <td><input type='text' name='school' value='<%=teacher.getSchool()%>'></td>
  </tr>

  <tr>
    <th>����</th>
      <td><input type='text' name='major' value='<%=teacher.getMajor()%>'></td>
  </tr>

  <tr>
    <th>���Ƿ�(�ñ�)</th>
      <td><input type='number' name='wage' value='<%=teacher.getWage()%>'></td>
  </tr>

  <tr>
    <th>�����</th>
      <td><%=teacher.getCreatedDate()%></td>
  </tr>

  </table>
  
<% 
    }
%>

<div>
  <button id='btn-list' type='button'>���</button>
  <button>����</button>
  <button id='btn-delete' type='button'>����</button>
</div>

</form>

<script>
document.querySelector('#btn-list').onclick = function() {
  location.href = 'list.jsp';
}
document.querySelector('#btn-delete').onclick = function() {
  var form = document.querySelector('#teacher-form');
  form.action = 'delete.jsp';
  form.submit();
}
</script>

</body>
</html>


