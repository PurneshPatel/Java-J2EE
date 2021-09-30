<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5>Session ID <%= session.getId() %></h5>
	<%--session.getAttribute("user_details").getUserName()  sent to clnt --%>
	<h5>Hello , ${sessionScope.user_details.userName}</h5>
	<%
	session.invalidate();
	%>
	<h5>You have logged out...</h5>
	<h5>
		<a href="login.jsp">Visit Again</a>
	</h5>


</body>
</html>