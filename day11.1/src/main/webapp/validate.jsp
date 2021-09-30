<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>

<%--Invoke ALL MATCHING setters of JB --%>
<jsp:setProperty property="*" name="user"/>
<body>
<%--Invoke B.L method of the JB w/o scriptlet , instead using EL--%>
<%-- session.getAttribute("user").authenticateUser()--%>
<%--WC : RD rd=request.getRD(session.getAttribute("user")).authenticateUser().concat(".jsp"); --%>
<%--rd.forward(request,response) --%>
<%-- <jsp:forward page="${sessionScope.user.authenticateUser()}.jsp"/> --%>
<%--WC : response.sendRedirect(response.encodeRedirectURL(session.getAttribute("user")).authenticateUser().concat(".jsp")) --%>
<c:redirect url="${sessionScope.user.authenticateUser()}.jsp"/>
</body>
</html>