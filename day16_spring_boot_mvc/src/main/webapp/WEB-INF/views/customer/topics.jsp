<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL supplied core tag lib --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%--import Spring supplied JSP tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%-- import spring supplied form tag lib --%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Validated Customer Details retrieved from session scope:
		${sessionScope.user_details}</h5>
	<%--Spring tag url : URL rewriting n creating URLs relative to root of  curnt web ctx --%>
	<spring:url var="url" value="/customer/tutorials" />
	<form:form action="${url}" method="get" modelAttribute="myTopic">
		<table style="background-color: lightgrey; margin: auto">
			<form:radiobuttons path="id" items="${requestScope.topic_list}"
				itemLabel="topicName" itemValue="id" />
			<tr>
				<td><input type="submit" value="Choose Topic" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>