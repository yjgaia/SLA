<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>스마트한 공유하기, YOG.IO!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${pageContext.request.contextPath}/style/prettify.css"
	type="text/css" rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/script/jquery.gritter.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/jquery.gritter.css" />
  <script src="${pageContext.request.contextPath}/script/jquery.percentageloader/jquery.percentageloader-0.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery.percentageloader/jquery.percentageloader-0.1.css"></script>
<style>
ul {
	word-wrap: break-word;
	word-break: break-all;
}
.achievement{
	height:100px;
	border: 1px solid;
	border-collapse: collapse;
	font-family: 맑은 고딕
}
.a_active{
	
}
.a_unactive{
	background: rgb(79,79,79);
}
.a_unactive .a_title{
	color: rgb(49,49,49);
}
.a_icon{
	width:15%;
	float:left;
	padding-right: 20px;
}
.a_content{
	width:70%;
	float:left;
	text-align: center;
}
.a_score{
	height:90%;
	margin-top: 20px;
	font-size:30px;
}
</style>
<script>
$(function(){
	console.log(${unIdentified});
	var datas=${unIdentified};
	for(var i=0;i<datas.length;i++){
		var data=datas[i];
		$.gritter.add({
			// (string | mandatory) the heading of the notification
			title: data.alias,
			// (string | mandatory) the text inside the notification
			text: data.description,
			// (string | optional) the image to display on the left
			image: 'http://a0.twimg.com/profile_images/59268975/jquery_avatar_bigger.png',
			// (bool | optional) if you want it to fade out on its own or just sit there
			sticky: false,
			// (int | optional) the time you want it to be alive for before fading out
			time: ''
		});
	}
	
});
</script>
</head>

<body>

	<div id="menu-wrapper">
		<div id="menu">
			<ul>
				<li><a href="${pageContext.request.contextPath}">홈</a></li>
				<li><a href="${pageContext.request.contextPath}/func/myanalyze">분석결과</a></li>
				<li class="current_page_item"><a href="${pageContext.request.contextPath}/func/achievement">업적</a></li>
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
							<div id="topLoader">      
				      		</div>
							<script>
        $(function() {
          var $topLoader = $("#topLoader").percentageLoader({width: 256, height: 256, controllable : false, progress : 0.5, onProgressUpdate : function(val) {
              
            }});

          var topLoaderRunning = false;
          $("#animateButton").click(function() {
            if (topLoaderRunning) {
              return;
            }
            topLoaderRunning = true;
           
            
          });
          $topLoader.setProgress(0);
          $topLoader.setValue('0');
          var kb = 0;
          var offset=10;
          var totalKb = ${total}*offset;
          
          var animateFunc = function() {
            kb += 1;
            $topLoader.setProgress(kb / totalKb);
            $topLoader.setValue(kb/offset+"개");
            
            if (kb < ${acquired}*offset) {
              setTimeout(animateFunc, 25);
            } else {
              topLoaderRunning = false;
            }
          }
          
          setTimeout(animateFunc, 25);
        });      
      </script>
		<div id="achievementList">
			<c:forEach items="${achievement}" var="achievement">
				<div class="achievement 
					<c:choose>
						<c:when test="${achievement.acquired eq -1 }">a_unactive</c:when>
						<c:otherwise>a_active</c:otherwise>
					</c:choose>
				">
					<div class="a_icon"><img src="${pageContext.request.contextPath}/img/logo.png" width="90%" height="90%"/></div>
					<div class="a_content">
						<div class="a_title"><h3>${achievement.alias }</h3></div>
						<div class="a_desc">${achievement.description }</div>
					</div>
					<div class="a_score">
						${achievement.score }
					</div>
				</div>
			</c:forEach>
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
