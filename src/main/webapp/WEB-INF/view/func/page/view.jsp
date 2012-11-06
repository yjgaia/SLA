<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>${command.title} | YOG.IO! 제공</title>
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
		</style>
		<script>
		$(function() {
			
		});
		</script>
	</head>
	
	<body>
	
		<div id="wrapper">
			<p>
				${command.content}
			</p>
			<script>YOGIO();</script>
			<a href="${pageContext.request.contextPath}/func/page/form?id=${command.id}">페이지 수정하기</a>
		</div>
		
	</body>
</html>
