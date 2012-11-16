<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>스마트한 공유하기, YOG.IO!</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="${pageContext.request.contextPath}/style/prettify.css"
	type="text/css" rel="stylesheet" />
<script
	src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
  <script src="${pageContext.request.contextPath}/script/jquery.percentageloader/jquery.percentageloader-0.1.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/script/jquery.percentageloader/jquery.percentageloader-0.1.css"></script>
<style>
ul {
	word-wrap: break-word;
	word-break: break-all;
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

#footer {
	padding: 10px;
	font-size: 12px;
	text-align: center;
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
</head>

<body>

	<div id="wrapper">
		<div id="header">
			<a id="logo" href="${pageContext.request.contextPath}/"><img
				border="0" src="${pageContext.request.contextPath}/img/logo.png"></a>
			<h3>업적 조회실</h3>
		</div>
		<div>
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
		</div>
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
		<div id="footer">
			&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a>
			3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>.
		</div>
	</div>

</body>
</html>
