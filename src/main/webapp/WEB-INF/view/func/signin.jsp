<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
		<style>
			h1 {
				font-size: 18px;
				margin: 0;
				padding: 0;
				margin-bottom: 10px;
			}
			.sns-button {
				float: left;
			}
			.sns-button h3 {
				font-size: 15px;
				margin: 0;
				padding: 0;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/func/myanalyze">분석결과</a></li>
				<li><a href="${pageContext.request.contextPath}/func/achievement">업적</a></li>
				<li><a href="${pageContext.request.contextPath}/func/page/create">페이지</a></li>
				<li><a href="${pageContext.request.contextPath}/func/intro">소개</a></li>
			</ul>
		</div>
		<!-- end #menu -->
	</div>
	<div id="header-wrapper">
	<div id="header">
			<div id="logo">
				<a href="#"><img src = "${pageContext.request.contextPath}/img/logo.png"></a>
			</div>
		</div>
	</div>
		<div id="wrapper">
			<div id="page">
				<div id="page-bgtop">
					<div id="page-bgbtm">
						<div id="content">
							<h1>어떤 SNS를 이용하십니까?</h1>
							<div class="sns-button">
								<h3>페이스북</h3>
								<form id="facebook-login-form" action="${pageContext.request.contextPath}/func/signin/facebook" method="POST">
									<input type="hidden" name="scope" value="email,publish_stream,offline_access,user_birthday,user_location" />
									<a href="javascript:$('#facebook-login-form').submit();" rel="tooltip" title="페이스북 로그인"><img alt="페이스북 로그인" src="${pageContext.request.contextPath}/img/facebook.png"></a>
							    </form>
							</div>
							<div class="sns-button">
							    <h3>트위터</h3>
							    <form id="twitter-login-form" action="${pageContext.request.contextPath}/func/signin/twitter" method="POST">
							    	<a href="javascript:$('#twitter-login-form').submit();" rel="tooltip" title="트위터 로그인"><img alt="트위터 로그인" src="${pageContext.request.contextPath}/img/twitter.png"></a>
								</form>
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
