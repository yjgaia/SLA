<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO!</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/style/prettify.css" type="text/css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/prettify.js"></script>
		<script src="${pageContext.request.contextPath}/script/js.js"></script>
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
			#go-analyze-result {
				margin-top: -3px;
				font-size: 15px;
				float: right;
				background-color: #11929E;
				color: #FFF;
				text-decoration: none;
				padding: 5px 10px;
				border-radius: 7px;
				box-shadow: #AAA 1px 1px 1px 0px;
			}
			#yogio-intro {
				height: 100px;
				background-color: #9AB285;
			}
			#attach-button-intro {
				background-color: #FFB896;
			}
			#attach-button-intro .code {
				margin: 0;
			}
			#create-page-intro {
				height: 100px;
				background-color: #FFF9B1;
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
				<a href="${pageContext.request.contextPath}"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
				<a href="${pageContext.request.contextPath}/func/analyze" id="go-analyze-result">분석 결과 보기</a>
			</div>
			<div id="yogio-intro">
				<p>
					YOG.IO!는 Short URL 서비스에 소셜 네트워크 분석 결과를 제공하는 서비스입니다.
				</p>
			</div>
			<div id="attach-button-intro">
				<p>
					사이트나 블로그에 YOG.IO! 공유하기 버튼을 붙혀보세요! 이렇게요!
				</p>
				<script>YOGIO();</script>
				<p>
					우선, HTML문서의 head에 다음과 같은 script를 추가합니다.
				</p>
				<pre class="code prettyprint linenums languague-html">&lt;script src="http://yog.io/script/js.js"&gt;&lt;/script&gt;</pre>
				<p>
					그리고, 원하는 위치에 다음과 같은 코드를 삽입합니다.
				</p>
				<pre class="code prettyprint linenums languague-html">&lt;script&gt;YOGIO();&lt;/script&gt;</pre>
				<p>
					커스터마이징도 가능합니다.
				</p>
				<pre class="code prettyprint linenums languague-html">&lt;script&gt;YOGIO({
    url: 'http://yog.io'
});&lt;/script&gt;</pre>
				<a href="${pageContext.request.contextPath}/func/button/parameters">파라미터 자세히 보기</a>
			</div>
			<div id="create-page-intro">
				<p>
					사이트나 블로그가 없다면 페이지를 만들어보세요!
					<br>
					이벤트 페이지나, 모임 공지등을 만들때에 유용합니다.
				</p>
				<a href="${pageContext.request.contextPath}/func/page/create">페이지 만들기</a>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
