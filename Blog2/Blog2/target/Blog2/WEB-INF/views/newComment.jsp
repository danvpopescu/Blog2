<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page session="true"%>
<html>
<head>
<meta charset="utf-8" />
<style>

#blog {
	font: 14px/24px Helvetica, Sans-Serif; 
	color: #7e7e7e;
	background: #f5f5f5;
	padding-bottom: 20px;
	align-content: center
}

#header {
	text-align: center;
	align-content: center
}

#heading {
	text-align: center;
	align-content: center
}

#main {
	text-align: left
	align-content: center
}

#comment {
	width: 900px;
	padding: 20px;
	align-content: center
	margin: 20px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;	
}

</style>
</head>

<body>
	<c:url value="/logout" var="logoutUrl" />

	<form action="${logoutUrl}" method="post" id="logoutForm">
		<!-- input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" /-->
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	
	<div id="blog" align="center">
		<div id="header">
			<h2 style="color:blue"> Comment</h1>
				<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="color:gray;"> User : ${pageContext.request.userPrincipal.name} | <a href="<c:url value='/searchArticle' />"> Search an Article</a> | <a href="<c:url value='/listArticles' />"> List of Articles</a> | <a href="javascript:formSubmit()"> Logout</a> </h3>
				</c:if>
	
		</div>

		<div id="comment" align="center">
		<form name='commentForm' action="<c:url value='/addComment' />" method='POST'>

			<table>
				<tr>
					<td>Title:</td>
				</tr>
				<tr>
					<td><input type='text' size="110" name='title' readonly="true"  value=${article.title}></td>
				</tr>
				<tr>
					<td>Content:</td>
				</tr>			
				<tr>
					<td><textarea rows="10" cols="110" name='content'></textarea></td>
				</tr>
				<tr>
					<td colspan='2'>
						<input name="addComment" type="submit" value="Save" />
						<button><a href="<c:url value='/listArticles' />">Cancel</a></button>
					</td>
				</tr>
			</table>

		</form>
		</div>

		<div id="footer">
		</div>
	</div>
</body>
</html>