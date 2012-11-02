<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO?</title>
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
			#sla-intro h4 {
				margin: 0;
				font-size: 14px;
			}
			img.profile {
				width: 100px;
				height: 100px;
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
				<p>
					YOG.IO!는 SW 마에스트로 3기 연수생인 심영재, 이한솔, 이희덕 군이 팀을 이루어 오우택 멘토님의 도움을 받아 진행하는 소셜 네트워크 공유-링크 분석시스템 개발 프로젝트 입니다.
				</p>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
