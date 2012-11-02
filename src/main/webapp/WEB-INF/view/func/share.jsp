<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
	<head>
		<meta charset="utf-8">
		<title>Share</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">		
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script>
			$(function() {
				$('#sharePost').submit(function() {
					$('#loading').show();
					
					var data = $(this).serialize();
					$.ajax({
						type: 'POST',
						url: '${pageContext.request.contextPath}/func/share',
						data: data,
						success: function(ok) {
							if (ok) {
								$('#loading').hide();
								window.close();
							}
						},
						dataType: 'json'
					});
					return false;
				});
			});
		</script>
		<style type="text/css">
		#loading {
			background-image: url(${pageContext.request.contextPath}/img/loading-bg.png);
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
			display: none;
		}
		</style>
	</head>
	<body>
		<form id="sharePost" action="${pageContext.request.contextPath}/func/share" method="POST">
			<table>
				<tr><td><textarea name="content" cols="60" rows="10"></textarea></td></tr>
				<tr><td>공유할 URL :<input type="text" name="url" value="${param.url}"/></td></tr>
				<tr><td><input type="submit" value="게시하기"/></td></tr>
			</table>
		</form>
		<table id="loading">
			<tr><td align="center">
			<img src="${pageContext.request.contextPath}/img/loading-msg.png">
			</td></tr>
		</table>
	</body>
</html>