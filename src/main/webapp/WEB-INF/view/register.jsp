<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome to the Login Page</title>
	</head> 
	<body>
		<c:url value="/login.do" var="authenticateURL" />
		<form action="${authenticateURL}" method="POST">
			<label for="username">Username</label>
			<input type="text" name="username" id="username"/>
			<br/>
			<label for="password">Password</label>
			<input type="password" name="password" id="password"/>
			<br/>
			<input type='checkbox' name='_spring_security_remember_me'/> Remember me on this computer.
			<br/>
			<input type="submit" value="Login"/>
		</form>
	</body>
</html>