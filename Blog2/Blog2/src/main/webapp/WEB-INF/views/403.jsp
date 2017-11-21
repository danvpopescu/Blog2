<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- 
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
 <head>
    <title>Security with Spring</title>
 </head>
 <body>
     <h1 style="color: blue">Access is Denied!</h1>
     <a style="color:blue" th:href="@{/}">Go to Home Page</a>
 </body>
</html>
-->


<html>
<body>
	<h1 style="color: blue">Access is Denied!</h1>
	<c:choose>
		<c:when test="${empty username}">
			<!-- h2>You do not have permission to access this page!</h2-->
		</c:when>
		<c:otherwise>
			<h2 style="color: blue">Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>

</body>
</html>