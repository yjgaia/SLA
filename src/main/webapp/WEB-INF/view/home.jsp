<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<sec:authentication property="principal" var="principal" />
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>TEST</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
	</head>
	
	<body>
	
		<sec:authorize access="isAuthenticated()">
		${principal.socialDisplayName}
		</sec:authorize>
	
		<!-- FACEBOOK login -->
		<form id="facebook-login-form" action="${pageContext.request.contextPath}/signin/facebook" method="POST">
			<input type="hidden" name="scope" value="email,publish_stream,offline_access" />
	    </form>
				    
		<a href="javascript:$('#facebook-login-form').submit();" rel="tooltip" title="페이스북 로그인"><img alt="페이스북 로그인" src="${pageContext.request.contextPath}/img/facebook/f_logo.jpg"></a>
		
	</body>
</html>
