<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 공유버튼 파라미터 자세히 보기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
		<link href="${pageContext.request.contextPath}/style/prettify.css" type="text/css" rel="stylesheet" />
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/prettify.js"></script>
		<style>
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
				padding: 10px;
				list-style: none;
				border-top: 1px solid #CCC;
				border-left: 5px solid #CCC;
				background-color: #FFF;
				font-size: 12px;
				color: #666;
			}
			#my-analyze-list li a {
				text-decoration: none;
				color: #333;
			}
			#my-analyze-list li a:hover {
				text-decoration: underline;
			}
			#my-analyze-list li .url {
				display: block;
				font-size: 16px;
				color: #18AAE0;
				font-weight: bold;
			}
			#my-analyze-list li .func {
				float: right;
				color: orange;
			}
		</style>
		<script>
		$(function() {
			prettyPrint();
		});
		</script>
	</head>
	
	<body>
	<div id="menu-wrapper">
		<div id="menu">
			<div style="display:table-cell;float:left;padding:15px 10px 0px 10px;">
				<img src = "${pageContext.request.contextPath}/img/logo.png">
			</div>
			<div id="menuin" style="display:table-cell">
			<ul>
				<li><a href="${pageContext.request.contextPath}">홈</a></li>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}/func/myanalyze">분석결과</a></li>
				<li><a href="${pageContext.request.contextPath}/func/achievement">업적</a></li>
				<li><a href="${pageContext.request.contextPath}/func/page/create">페이지</a></li>
				<li><a href="${pageContext.request.contextPath}/func/intro">소개</a></li>
			</ul>
			</div>
		</div>
		<!-- end #menu -->
	</div>
		<div id="wrapper">
			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">
							<div id="my-analyze-list">
								<p>
									총 <b>${list.size()}</b>개 입니다.
								</p>
								<ul>
									<c:forEach items="${list}" var="item">
									<li>
										<a class="url" href="${pageContext.request.contextPath}/func/analyze?shortUrl=${item.shortUrl}" target="_blank">${item.url}</a>
										<br/><b>${item.visitCountSum}</b>명 방문, <b>${item.likeCount }</b>명이 좋아하고 <b>${item.commentCount }</b>명이 댓글을 달았습니다.
										<a class="func" href="javascript:if(confirm('정말 삭제 하시겠습니까?')){location.href='${pageContext.request.contextPath}/func/delanalyze?shortUrl=${item.shortUrl}';}">삭제</a>
									</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="footer">
	<p>&copy; 2012 Yog.io | SW Maestro SLA Team.</p>
	</div>
	</body>
</html>
