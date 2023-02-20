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
  private StudentDao studentDao;

  @Override
  public void init() {
    ServletContext ctx = getServletContext();
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

<!DOCTYPE html>
<html>
<head>
<meta charset='UTF-8'>
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>�л�(JSP)</h1>

<% 
    int studentNo = Integer.parseInt(request.getParameter("no"));
    Student student = this.studentDao.findByNo(studentNo);

    if (student == null) {
%>

    <p>�ش� ��ȣ�� �л��� �����ϴ�.</p>

<% 
    } else {
%>
    	
  <form id='student-form' action='update' method='post'>

  <table border='1'>

  <tr>
    <th>��ȣ</th>
      <td><input type='text' name='no' value='<%=student.getNo()%>'></td>
  </tr>

  <tr>
    <th>�̸�</th>
      <td><input type='text' name='name' value='<%=student.getName()%>'></td>
  </tr>

  <tr>
    <th>�̸���</th>
      <td><input type='email' name='email' value='<%=student.getEmail()%>'></td>
  </tr>

  <tr>
    <th>��ȣ</th>
    <td><input type='password' name='password'></td>
  </tr>

  <tr>
    <th>��ȭ</th>
      <td><input type='tel' name='tel' value='<%=student.getTel()%>'></td>
  </tr>

  <tr>
    <th>�����ȣ</th>
      <td><input type='text' name='postNo' value='<%=student.getPostNo()%>'></td>
  </tr>

  <tr>
    <th>�⺻�ּ�</th>
      <td><input type='text' name='basicAddress' value='<%=student.getBasicAddress()%>'></td>
  </tr>

  <tr>
    <th>���ּ�</th>
      <td><input type='tel' name='detailAddress' value='<%=student.getDetailAddress()%>'></td>
  </tr>

  <tr>
    <th>��������</th>
      <td><input type='checkbox' name='working' value='<%=student.isWorking() ? "checked" : ""%>'> ������</td>
  </tr>

  <tr>
    <th>����</th>
      <td><input type='radio' name='gender' value='M' <%=student.getGender() == 'M' ? "checked" : ""%>> ��
          <input type='radio' name='gender' value='W' <%=student.getGender() == 'W' ? "checked" : ""%>> ��</td> 
  </tr>

  <tr>
    <th>����</th>
      <td><select name='level'>
            <option value='0' <%=student.getLevel() == 0 ? "selected" : ""%>>��������</option>
            <option value='1' <%=student.getLevel() == 1 ? "selected" : ""%>>��������</option>
            <option value='2' <%=student.getLevel() == 2 ? "selected" : ""%>>������</option>
          </select></td>
  </tr>

  <tr>
    <th>�����</th>
      <td><%=student.getCreatedDate()%></td>
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
  var form = document.querySelector('#student-form');
  form.action = 'delete.jsp';
  form.submit();
}
</script>

</body>
</html>


