<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>SLA 팀 소개</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
		<style>
			html, body {
				font-family: Malgun Gothic;
			}
			#wrapper {
				margin: auto;
				width: 700px;
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
			#sla-intro .top {
				text-align: center;
			}
			#sla-intro .top p {
				text-align: left;
			}
			#sla-intro div {
			}
			#sla-intro h3 {
				margin: 0;
				padding: 10px;
			}
			#sla-intro h4 {
				margin: 0;
				font-size: 16px;
				text-align: center;
				margin-bottom: 5px;
			}
			img.profile {
				width: 100px;
				height: 100px;
			}
			#sla-intro p {
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
	
	<div id="menu-wrapper">
		<div id="menu">
			<div style="display:table-cell;float:left;padding:5px 10px 0px 10px;">
				<a href="${pageContext.request.contextPath}/"><img src = "${pageContext.request.contextPath}/img/logo_small.png" border="0"></a>
			</div>
			<div id="menuin" style="display:table-cell">
			<ul>
				<li><a href="${pageContext.request.contextPath}/">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/func/myanalyze">분석결과</a></li>
				<li><a href="${pageContext.request.contextPath}/func/achievement">업적</a></li>
				<li><a href="${pageContext.request.contextPath}/func/page/create">페이지</a></li>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}/func/intro">소개</a></li>
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
			<div id="sla-intro">
				<h3>SLA 팀 소개</h3>
				<div class="top">
					<h4>SLA: Shared Link Analyzer</h4>
					<img src="${pageContext.request.contextPath}/img/swmaestro.jpg">
					<p>
						SLA는 SW 마에스트로 3기 연수생인 심영재, 이한솔, 이희덕 군이 팀을 이루어 오우택 멘토님의 도움을 받아 진행하는 소셜 네트워크 공유-링크 분석시스템 개발 프로젝트 팀 입니다.
					</p>
				</div>
				<table>
					<tr>
						<td>
							<h4>오우택 멘토</h4>
							<img class="profile" src="${pageContext.request.contextPath}/img/owt.jpg">
						</td>
						<td>
							<p>
								부끄럽다 얘들아. 자랑스러운 프로젝트가 될 수 있도록 더 열심히 멋진 결과물 만들자.
							</p>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<h4>심영재</h4>
							<img class="profile" src="${pageContext.request.contextPath}/img/syj.jpg">
						</td>
						<td>
							<p>
								항상 우리팀, 우리 멘토님이 최고라고 생각합니다. 짧은 시간이지만 열심히 노력하겠습니다. 화이팅!
							</p>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<h4>이한솔</h4>
							<img class="profile" src="${pageContext.request.contextPath}/img/lhs.jpg">
						</td>
						<td>
							<p>
								누가 시켜서 하는게 아닌 우리가 만들고 싶은 걸 우리가 만들고 싶은 방식으로 만든다는 것 그 자체만으로도 의미가 크다고 생각합니다. YOG.IO 화이팅!
							</p>
						</td>
					</tr>
				</table>
				<table>
					<tr>
						<td>
							<h4>이희덕</h4>
							<img class="profile" src="${pageContext.request.contextPath}/img/lhd.jpg">
						</td>
						<td>
							<p>
								나는 차가운 도시개발자 하지만 내 코드에겐 따뜻하겠지...
							</p>
						</td>
					</tr>
				</table>
			</div>
			</div>
			</div>
			</div>
			</div>
		</div>
		<div id="footer">
			<p>&copy; 2012 Yog.io | SW Maestro <a href="${pageContext.request.contextPath}/func/sla/intro">SLA Team.</a></p>
		</div>	
	</body>
</html>
