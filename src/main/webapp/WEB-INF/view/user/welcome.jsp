<%--
  Created by IntelliJ IDEA.
  User: codingBoy
  Date: 16/10/8
  Time: 下午4:41
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Welcome!</h1>
<c:choose>
    <c:when test="${ empty sessionScope.sessionUser}">You have not sign in yet，<a href="<c:url value='/WEB-INF/view/user/login.jsp'/> ">Click here to visit the login page.</a> </c:when>
    <c:otherwise>Hey my dear，${sessionScope.sessionUser.username} </c:otherwise>
</c:choose>
</body>
</html>
