<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<!-- FACEBOOK login -->
		<form id="facebook-login-form" action="${pageContext.request.contextPath}/func/signin/facebook" method="POST">
			<input type="hidden" name="scope" value="email,publish_stream,offline_access" />
	    </form>
	    <script type="text/javascript">
	    $('#facebook-login-form').submit();
	    </script>
	</body>
</html>
