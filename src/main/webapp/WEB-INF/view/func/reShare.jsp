<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Share</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
	</head>
	<body>
		<form id="reSharePost">
		<table>
			<tr><td><textarea cols="60" rows="10"></textarea></td></tr>
			<tr><td>공유할 URL : ${shortUrl.url }</td></tr>
			<tr><td><button>게시하기</button></td></tr>
		</table>
		</form>
	</body>
</html>