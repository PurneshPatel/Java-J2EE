<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Hello, ${sessionScope.user.validatedUserDetails.name}</h5>
	<h5>Status Message : ${sessionScope.topic_tut.message}</h5>
	<%
	session.invalidate();
	%>
	<h5>You have logged out ....</h5>
	<h5>
		<a href="login.jsp">Visit Again</a>
	</h5>
</body>
</html>