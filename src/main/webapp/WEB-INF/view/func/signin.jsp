<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<style>
			h1 {
				font-size: 18px;
				margin: 0;
				padding: 0;
			}
			.sns-button h3 {
				font-size: 15px;
				margin: 0;
				padding: 0;
			}
		</style>
	</head>
	<body>
		<h1>어떤 SNS에 공유하시겠습니까?</h1>
		<div class="sns-button">
			<!-- FACEBOOK login -->
			<h3>페이스북</h3>
			<form id="facebook-login-form" action="${pageContext.request.contextPath}/func/signin/facebook" method="POST">
				<input type="hidden" name="scope" value="email,publish_stream,offline_access" />
				<a href="javascript:$('#facebook-login-form').submit();" rel="tooltip" title="페이스북 로그인"><img alt="페이스북 로그인" src="${pageContext.request.contextPath}/img/facebook/f_logo.jpg"></a>
		    </form>
	    </div>
	</body>
</html>
