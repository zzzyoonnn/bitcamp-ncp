<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>��Ʈķ�� - NCP 1��</title>
</head>
<body>
<h1>���ǰ����ý���!</h1>
<ul>
  <li><a href="student/list">�л�����</a></li>
  <li><a href="teacher/list">�������</a></li>
  <li><a href="board/list">�Խ���</a></li>
  
<c:if test="${empty loginUser}">
  <li><a href="auth/form">�α���</a></li>
</c:if>

<c:if test="${not empty loginUser}">
  <li><a href="auth/logout">�α׾ƿ�(${loginUser.name})</a></li>
</c:if>

</ul>
</body>
</html>