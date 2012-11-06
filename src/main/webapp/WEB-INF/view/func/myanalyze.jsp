<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 공유버튼 파라미터 자세히 보기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/style/prettify.css" type="text/css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/prettify.js"></script>
		<style>
			html, body {
				font-family: Malgun Gothic;
			}
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
				height: 32px;
			}
			#my-analyze-list {
				background-color: #FFB896;
			}
			#my-analyze-list h3 {
				margin: 0;
				padding: 10px;
			}
			#footer {
				padding: 10px;
				font-size: 12px;
				text-align: center;
			}
		</style>
		<script>
		$(function() {
			prettyPrint();
		});
		</script>
	</head>
	
	<body>
	
		<div id="wrapper">
			<div id="header">
				<a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
			</div>
			<div id="my-analyze-list">
				<h3>내 분석 결과 리스트</h3>
				<ul>
					<c:forEach items="${list}" var="item">
					<li><a href="${pageContext.request.contextPath}/func/analyze?shortUrl=${item.shortUrl}" target="_blank">${item.url}</a> <a href="javascript:alert('준비중입니다.');">삭제</a></li>
					</c:forEach>
				</ul>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
