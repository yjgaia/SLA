<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal" var="principal" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>POST</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
	</head>
	
	<body>
	
		<sec:authorize access="isAuthenticated()">
		${principal.socialDisplayName}
		</sec:authorize>
	</body>
</html>
