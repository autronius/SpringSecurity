<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    
<html>
	<head>
		<meta charset="utf-8">
		<title>Welcome</title>
	</head> 
	<body>
		<h2>Yo Yo Yo!</h2>
		<br />
		<br />
		<h2>Proxy Port is: ${proxyPort}</h2>
		
		<br />
		<br />
		<sec:authorize ifAllGranted="ROLE_ANONYMOUS">
			<h2>Ads go Here</h2>
		</sec:authorize>
	</body>
</html>
