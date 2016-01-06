<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome to the Secure Home Page</title>
	</head> 
	<body>
		The Secure Home Page!
		<br/>
		<h1>"${handlerResult}"</h1>
	</body>
</html>
