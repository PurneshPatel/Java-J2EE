<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL supplied core tag library --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%--session.getAttribute("user").getMessage() --%>
	<h5 align="center" style="color: green">${sessionScope.user.message}</h5>
	<%--WC : session.getAttribute("user").getValidatedUserDetails().getName() --%>
	<h5>Hello , ${sessionScope.user.validatedUserDetails.name}</h5>
	<h5 align="center" style="color: red">${sessionScope.topic_tut.message}</h5>
	<form action="process_form.jsp" method="get">
		<table style="background-color: lightgrey; margin: auto">
			<caption>Add New Tutorial Form</caption>
			<tr>
				<td>Topic</td>
				<td><select name="topicId">
						<%--for(Topic topic : sesison.getAttribute("topic_tut").getTopics()) {...} --%>
						<c:forEach var="topic" items="${sessionScope.topic_tut.topics}">
							<option value="${topic.topicId}">${topic.topicName}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>Tutorial Title</td>
				<td><input type="text" name="tutName" /></td>
			</tr>
			<tr>
				<td>Tutorial Author</td>
				<td><input type="text" name="tutAuthor" /></td>
			</tr>
			<tr>
				<td>Publish Date</td>
				<td><input type="date" name="pubDate" /></td>
			</tr>
			<tr>
				<td>Visits</td>
				<td><input type="number" name="visits" /></td>
			</tr>
			<tr>
				<td>Contents</td>
				<td><textarea name="contents"></textarea>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="Add New Tutorial"></td>
			</tr>
		</table>
	</form>
</body>
</html>