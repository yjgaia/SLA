<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO?</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/jquery.gritter.js"></script>
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/jquery.gritter.css" />
		<style>
			html, body {
				font-family: Malgun Gothic;
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
				width: 150px;
				height: 150px;
			}
		</style>
		<script>
		$(function() {
			$('#add-regular').click(function(){
				
				$.gritter.add({
					// (string | mandatory) the heading of the notification
					title: 'This is a regular notice!',
					// (string | mandatory) the text inside the notification
					text: 'This will fade out after a certain amount of time. Vivamus eget tincidunt velit. Cum sociis natoque penatibus et <a href="#" style="color:#ccc">magnis dis parturient</a> montes, nascetur ridiculus mus.',
					// (string | optional) the image to display on the left
					image: 'http://a0.twimg.com/profile_images/59268975/jquery_avatar_bigger.png',
					// (bool | optional) if you want it to fade out on its own or just sit there
					sticky: false,
					// (int | optional) the time you want it to be alive for before fading out
					time: ''
				});

				return false;

			});
		});
		</script>
	</head>
	
	<body>
	<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li><a href="${pageContext.request.contextPath}">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/func/myanalyze">분석결과</a></li>
				<li><a href="${pageContext.request.contextPath}/func/achievement">업적</a></li>
				<li><a href="${pageContext.request.contextPath}/func/page/create">페이지</a></li>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}/func/intro">소개</a></li>
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
								<div id="sla-intro">
								<p>
									YOG.IO!는 SW 마에스트로 3기 연수생인 심영재, 이한솔, 이희덕 군이 팀을 이루어 오우택 멘토님의 도움을 받아 진행하는 소셜 네트워크 공유-링크 분석시스템 개발 프로젝트 입니다.
								</p>
								<p>
									<b><br/>무엇을 얻을 수 있나요?</b>
								</p>
								<p>
									<table>
										<tr>
											<td><a href="${pageContext.request.contextPath }/img/sample.jpg" target="_blank"><img border="0" src="${pageContext.request.contextPath }/img/sample.jpg" width="350" height="500"/></a></td>
											<td valign="top">YOG.IO!를 이용해 링크를 SNS에 공유할 시 해당 SNS를 통해 링크를 방문하는 사용자의 각종 통계를 알 수 있습니다. 시간별 방문자, 누적 방문자, 공유 영향력 순위, 운영체제, 브라우저 분포 등등
											각종 통계를 확인 할 수 있습니다. 해당 고유한 링크에 대해 정보가 수집되므로 특정 이벤트 페이지나 사용자에게 얼마나 보여지고 있는지를 알아야 하는 페이지에
											쉽게 적용 가능합니다.<br/>내가 공유를 했는데 과연 얼마나 많은 사람들이 보고 있는지, 나랑 같은 링크를 공유한 사람들은 어떤지 궁금하지 않으셨나요?<br/>
											<br/><b>지금 당장 공유를 시작해 보세요!</b><br/><a href="http://yog.io">공유하기</a></td>
										</tr>
									</table>
								</p>
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
