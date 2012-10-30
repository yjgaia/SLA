<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 공유버튼 파라미터 자세히 보기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
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
			#sla-intro {
				background-color: #EEE;
			}
			#sla-intro h3 {
				margin: 0;
				padding: 10px;
			}
			#sla-intro img {
				width: 150px;
			}
			#footer {
				padding: 10px;
				font-size: 12px;
				text-align: center;
			}
		</style>
		<script>
		$(function() {
			
		});
		</script>
	</head>
	
	<body>
	
		<div id="wrapper">
			<div id="header">
				<a href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
			</div>
			<div id="sla-intro">
				<h3>SLA 팀 소개</h3>
				<img src="${pageContext.request.contextPath}/img/syj.jpg">
				<img src="${pageContext.request.contextPath}/img/lhs.jpg">
				<img src="${pageContext.request.contextPath}/img/lhd.jpg">
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
