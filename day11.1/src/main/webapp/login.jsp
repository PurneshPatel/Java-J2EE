<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%--Create JB instance w/o java code : jsp:useBean --%>
<%--After checking : in case of null --session.setAttribute("user",new UserBean()) --%>
<jsp:useBean id="user" class="beans.UserBean" scope="session" />

<body>
<h5 align="center" style="color: red">${sessionScope.user.message}</h5>
 <form action="validate.jsp" method="post">
      <table style="background-color: lightgrey; margin: auto">
        <tr>
          <td>Enter User Email</td>
          <td><input type="text" name="email" /></td>
        </tr>
        <tr>
          <td>Enter Password</td>
          <td><input type="password" name="password" /></td>
        </tr>

        <tr>
          <td><input type="submit" value="Login" /></td>
        </tr>
      </table>
    </form>

</body>
</html>