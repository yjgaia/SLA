<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 페이지 만들기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		<script src="${pageContext.request.contextPath}/script/js.js"></script>
		<style>
			#wrapper {
				margin: auto;
				width: 600px;
				padding-top: 30px;
			}
			#wrapper p {
				font-size: 15px;
				margin: 0;
				padding: 10px;
			}
			#header {
				height: 36px;
			}
			#logo {
				float: left;
			}
			#logo img {
				border: none;
			}
			#create-page-form {
				clear: both;
			}
			#footer {
				padding: 10px;
				font-size: 11px;
				text-align: center;
				color: #999;
			}
			#footer .left {
				float: left;
			}
			#footer .right {
				float: right;
			}
			#footer a {
				color: #999;
			}
		</style>
		<script>
		$(function() {
			
		});
		</script>
	</head>
	
	<body>
	
		<div id="wrapper">
			<form:form>
				<div id="header">
					<a id="logo" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
				</div>
				<div id="create-page-form">
					<label>제목 <form:input path="title" /></label>
					<label>비밀번호 <form:password path="password" /></label>
					<input type="submit">
				</div>
			</form:form>
			<div id="footer">
				<div class="left">
					<script>YOGIO();</script>
				</div>
				<div class="right">
					&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>
				</div>
			</div>
		</div>
		
	</body>
</html>
