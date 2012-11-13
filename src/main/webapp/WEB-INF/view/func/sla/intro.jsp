<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>SLA 팀 소개</title>
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
				<a href="${pageContext.request.contextPath}/"><img border="0" src="${pageContext.request.contextPath}/img/logo.png"></a>
			</div>
			<div id="sla-intro">
				<h3>SLA 팀 소개</h3>
				<div>
					<h4>SLA: Shared Link Analyzer</h4>
					<img src="${pageContext.request.contextPath}/img/swmaestro.jpg">
					<p>
						SLA는 SW 마에스트로 3기 연수생인 심영재, 이한솔, 이희덕 군이 팀을 이루어 오우택 멘토님의 도움을 받아 진행하는 소셜 네트워크 공유-링크 분석시스템 개발 프로젝트 팀 입니다.
					</p>
				</div>
				<div>
					<h4>오우택 멘토</h4>
					<img class="profile" src="${pageContext.request.contextPath}/img/owt.jpg">
					<p>
						부끄럽다 얘들아. 자랑스러운 프로젝트가 될 수 있도록 더 열심히 멋진 결과물 만들자.
					</p>
				</div>
				<div>
					<h4>심영재</h4>
					<img class="profile" src="${pageContext.request.contextPath}/img/syj.jpg">
					<p>
						항상 우리팀, 우리 멘토님이 최고라고 생각합니다. 짧은 시간이지만 열심히 노력하겠습니다. 화이팅!
					</p>
				</div>
				<div>
					<h4>이한솔</h4>
					<img class="profile" src="${pageContext.request.contextPath}/img/lhs.jpg">
				</div>
				<div>
					<h4>이희덕</h4>
					<img class="profile" src="${pageContext.request.contextPath}/img/lhd.jpg">
					<p>
						나는 차가운 도시개발자 하지만 내 코드에겐 따뜻하겠지...
					</p>
				</div>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
