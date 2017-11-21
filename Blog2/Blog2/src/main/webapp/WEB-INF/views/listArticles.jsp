<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<!-- 
<html xmlns="http://www.w3.org/1999/xhtml" xmlns:th="http://www.thymeleaf.org" xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3">
</html>
 -->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page session="true"%>
<html>
<head>
<meta charset="utf-8" />
<style>

#blog {
	font: 14px/24px Helvetica, Sans-Serif; 
	color: #7e7e7e;
	background: #f5f5f5;
	padding-bottom: 20px;
}

#header {
	text-align: center;
}

#heading {
	text-align: center;
}

#main {
	text-align: left
	
}
#article {
	width: 900px;
	padding: 20px;
	
	margin: 20px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;	
}

#comment {
	align-content: right
	text-align: left
	width: 300px;
	padding: 20px;
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

	<div id="blog">
		<div id="header">
			
			<!-- sec:authorize access="hasRole('USER')"-->
				<h2 style="color:blue">The list of articles!</h2>
			<!-- /sec:authorize-->

				<c:if test="${pageContext.request.userPrincipal.name != null}">
			<h3 style="color:gray;">User : ${pageContext.request.userPrincipal.name} | <a href="<c:url value='/searchAnArticle' />"> Search an Article</a> | <a href="<c:url value='/listArticles' />"> List of Articles</a> | <a href="javascript:formSubmit()"> Logout</a> </h3>
				</c:if>
			
				<a href="<c:url value='/newArticle' />">Add New Article</a>	
		</div>

		<form>
			<div id=content>
				<div id="side">	 </div>
				<div id="main">
					<c:forEach items="${articles}" var="article">
					<c:set value="${article.title}" var="title"/>
					<div id="article">
						<div id="heading"></div>
						<table>
			                <thead>
			                    <tr>
			                        <th>Title :${article.title}</th>
			                    </tr>
			                    <tr>    
			                        <th>Posted: ${article.postedDate}</th>
			                        <th>By:${article.username}</th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <tr>
			                    	<td>${article.content}<td>
			                    </tr>
			                </tbody>
			                <tfoot>
			                </tfoot>
						</table>

						<div id="footer" align="right">
							
							
							<table><tr>
	                        <!-- sec:authorize access="hasRole('ADMIN')"-->
	                        	
	                        <c:if test="${pageContext.request.userPrincipal.name == article.username}">	
	                        	<td><a href="<c:url value='/editArticle'><c:param name='title' value="${article.title}" /></c:url>">Edit Article</a></td>
	                        <!-- /sec:authorize-->
	                        <!-- sec:authorize access="hasRole('ADMIN')"-->
	                            <td><a href="<c:url value='/deleteArticle'><c:param name='title' value="${article.title}" /></c:url>">Delete Article</a></td>
	                        <!-- /sec:authorize-->
	                        </c:if>	
	                        	<td><a href="<c:url value='/newComment'><c:param name='title' value="${article.title}" /></c:url>">Add New Comment</a></td>
	                        </tr></table>
	                        
						</div>
						
						<div id="heading" ></div>
							
							<c:forEach items="${artComments}" var="entry">
							<c:set value="${entry.key}" var="titleComm"/>
								<c:if test="${title==titleComm}">
									<c:forEach items="${entry.value}" var="comment">
									<div id="comment">
										<table>
											<tr><td>		
												Posted: <c:out value="${comment.postedDate}"/> By: <c:out value="${comment.username}"/>
											</td></tr>
											<tr><td>		
												Comment: <c:out value="${comment.content}"/>
											</td></tr>

					                        <c:if test="${pageContext.request.userPrincipal.name == comment.username}">	
					                        	<tr><td><a href="<c:url value='/editComment'><c:param name='commentId' value="${comment.commentId}" /></c:url>">Edit Comment</a>
					                            <a href="<c:url value='/deleteComment'><c:param name='commentId' value="${comment.commentId}" /></c:url>">Delete Comment</a></td></tr>
					                        </c:if>	
										</table>
									</div>					
									</c:forEach>
								</c:if>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
			</div>
		</form>
		<div id="footer">
		</div>
	</div>
</body>
</html>