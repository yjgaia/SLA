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
			ul{
				word-wrap:break-word;
				word-break:break-all;
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
			#header h3 {
				padding: 0;
				padding-top: 1px;
				margin: 0;
				margin-left: 20px;
				float: left;
			}
			#logo {
				float: left;
			}
			#logo img {
				border: none;
			}
			#my-analyze-list {
				background-color: #EEE;
				border: 1px solid #CCC;
				border-radius: 10px 10px 0 0;
			}
			#my-analyze-list p {
				font-size: 12px;
			}
			#my-analyze-list ul {
				margin: 0;
				padding: 0;
			}
			#my-analyze-list li {
				margin: 0;
				padding: 5px;
				list-style: none;
				border-top: 1px solid #CCC;
				border-left: 5px solid #CCC;
				background-color: #FFF;
				font-size: 12px;
			}
			#my-analyze-list li a {
				text-decoration: none;
				color: #333;
			}
			#my-analyze-list li a:hover {
				text-decoration: underline;
			}
			#my-analyze-list li .func {
				float: right;
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
				<a id="logo" href="${pageContext.request.contextPath}/"><img border="0" src="${pageContext.request.contextPath}/img/logo.png"></a>
				<h3>내 분석 결과 리스트</h3>
			</div>
			<div id="my-analyze-list">
				<p>
					총 <b>${list.size()}</b>개 입니다.
				</p>
				<ul>
					<c:forEach items="${list}" var="item">
					<li>
						<a href="${pageContext.request.contextPath}/func/analyze?shortUrl=${item.shortUrl}" target="_blank">${item.url}</a>
						<a class="func" href="${pageContext.request.contextPath}/func/delanalyze?shortUrl=${item.shortUrl}">삭제</a>
					</li>
					</c:forEach>
				</ul>
			</div>
			<div id="footer">
				&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
			</div>
		</div>
		
	</body>
</html>
