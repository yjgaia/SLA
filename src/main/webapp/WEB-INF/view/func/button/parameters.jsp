<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
			#attach-button-parameters-intro {
				background-color: #FFB896;
			}
			#attach-button-parameters-intro h3 {
				margin: 0;
				padding: 10px;
			}
			#attach-button-parameters-intro .code {
				margin: 0;
			}
			#attach-button-parameters-intro ul {
				padding: 10px 10px 10px 35px;
				margin: 0;
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
			<div id="attach-button-parameters-intro">
				<h3>공유버튼 파라미터 자세히 보기</h3>
				<pre class="code prettyprint linenums languague-html">&lt;script&gt;YOGIO({
    url: 'http://yog.io'
});&lt;/script&gt;</pre>
				<ul>
					<li>url: 공유하려는 URL</li>
				</ul>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
