<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>�α���</h1>
<form action="login" method="post">
<c:if test="${error == 'loginfail'}">
  <p>�̸��� �Ǵ� ��ȣ�� ���� �ʽ��ϴ�!</p>
</c:if>
<table border="1">

<tr>
  <th>ȸ�� ����</th>
  <td>
    <input type="radio" name="usertype" value="student" checked> �л�
    <input type="radio" name="usertype" value="teacher"> ����
  </td>
</tr>

<tr>
  <th>�̸���</th>
  <td><input type="email" name="email" value=${cookie.email.value}></td>
</tr>

<tr>
  <th>��ȣ</th>
  <td><input type="password" name="password"></td>
</tr>
</table>

<div>
  <input type="checkbox" name="saveEmail">�̸��� ����<br>
  <button>�α���</button>
</div>

</form>
</body>
</html>