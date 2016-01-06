<!DOCTYPE html>
<!-- http://docs.spring.io/spring-security/site/docs/3.0.x/reference/el-access.html -->
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<c:url value="/showMessage.jsp" var="messageUrl" />
		<a href="${messageUrl}">Click to enter</a>
		
		<br />
		<br />
		<c:url value="/secure/secureHomePage.shtml" var="securemessageUrl" />
		<a href="${securemessageUrl}">Go To Secure Page</a>
		
		<br />
		<br />
		<c:url value="/andrewradford.shtml" var="andrewmessageUrl" />
		<a href="${andrewmessageUrl}">Go To Andrew Home Page</a>
		
		<br />
		<br />
		<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
			<h2>You Are Not Logged IN</h2>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()"> <%-- "hasAnyRole('ROLE_USER', 'ROLE_ADMIN')"> --%>
			<c:url value="/logout.do" var="logoutURL" />
			<a href="${logoutURL}">Logout</a>
		</sec:authorize>

		<br />
		<br />
		<sec:authorize access="isAnonymous()">
			<c:url value="/login" var="loginURL" />
			<a href="${loginURL}">LogIN</a>
		</sec:authorize>
	</body>
</html>
