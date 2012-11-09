<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="utf-8">
<title>Share</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script>
	$(function() {
		$('#sharePost').submit(function() {
			$('#loading').show();

			var data = $(this).serialize();
			$.ajax({
				type : 'POST',
				url : '${pageContext.request.contextPath}/func/share',
				data : data,
				success : function(ok) {
					if (ok) {
						$('#loading').hide();
						window.close();
					}
				},
				dataType : 'json'
			});
			return false;
		});
	});
</script>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script
	src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
<script src="${pageContext.request.contextPath}/script/js.js"></script>
<style>
#wrapper {
	margin: auto;
	width: 600px;
	padding-top: 30px;
	
}
td{
	font-family:Arial;
	word-wrap:break-word;
	word-break:break-all;
}
.layer {
	height: 150px;
}

.layer h3 {
	margin: 0;
	padding: 0;
	font-size: 16px;
	font-weight: bold;
	color: #fff;
	text-shadow: #000 0px 1px 2px;
}

.layer p {
	margin: 4px 0 10px 0;
	padding: 0;
	font-size: 16px;
	color: #fff;
	text-shadow: #000 0px 1px 2px;
}

.layer-inner {
	padding: 10px;
}

.layer-inner .button {
	display: inline-block;
	outline: none;
	cursor: pointer;
	text-align: center;
	text-decoration: none;
	font: 14px/100% Arial, Helvetica, sans-serif;
	padding: .5em 2em .55em;
	text-shadow: 0 1px 1px rgba(0, 0, 0, .3);
	-webkit-border-radius: .5em;
	-moz-border-radius: .5em;
	border-radius: .5em;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .2);
}

.layer-inner .button:hover {
	text-decoration: none;
}

.layer-inner .button:active {
	position: relative;
	top: 1px;
}

.button.orange {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a),
		to(#f47a20) );
	background: -moz-linear-gradient(top, #faa51a, #f47a20);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}

.button.orange:hover {
	background: #f47c20;
	background: -webkit-gradient(linear, left top, left bottom, from(#f88e11),
		to(#f06015) );
	background: -moz-linear-gradient(top, #f88e11, #f06015);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11',
		endColorstr='#f06015' );
}

.button.orange:active {
	color: #fcd3a5;
	background: -webkit-gradient(linear, left top, left bottom, from(#f47a20),
		to(#faa51a) );
	background: -moz-linear-gradient(top, #f47a20, #faa51a);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20',
		endColorstr='#faa51a' );
}

#header {
	height: 32px;
}

#logo {
	float: left;
}

#logo img {
	border: none;
}

#go-intro {
	font-size: 12px;
	color: blue;
}
#share-form {
	clear: both;
	margin-top: 4px;
	margin-bottom: 8px;
}

#share-form p {
	margin: 0;
	padding: 10px 10px 0 10px;
	font-size: 12px;
	color: #FFF;
}

#share-form form {
	overflow: auto;
}

#share-form .url {
	float: left;
	margin: 0;
	border: solid 1px #EEE;
	border-radius: .3em;
	background-color: #FFF;
	padding: 5px;
	width: 470px;
	font-size: 15px;
	font-weight: bold;
}

#share-form .submit {
	float: right;
	margin: 0;
	cursor: pointer;
	border-radius: .3em;
	background-color: #FFF;
	padding: 5px;
	font-size: 15px;
	font-weight: bold;
	width: 80px;
}

#share-form .submit {
	color: #fef4e9;
	border: solid 1px #da7c0c;
	background: #f78d1d;
	background: -webkit-gradient(linear, left top, left bottom, from(#faa51a),
		to(#f47a20) );
	background: -moz-linear-gradient(top, #faa51a, #f47a20);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a',
		endColorstr='#f47a20' );
}

#share-form .submit:hover {
	background: #f47c20;
	background: -webkit-gradient(linear, left top, left bottom, from(#f88e11),
		to(#f06015) );
	background: -moz-linear-gradient(top, #f88e11, #f06015);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11',
		endColorstr='#f06015' );
}

#share-form .submit:active {
	color: #fcd3a5;
	background: -webkit-gradient(linear, left top, left bottom, from(#f47a20),
		to(#faa51a) );
	background: -moz-linear-gradient(top, #f47a20, #faa51a);
	filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20',
		endColorstr='#faa51a' );
}

#footer {
	padding: 10px;
	font-size: 11px;
	text-align: center;
	color: #999;
	margin-bottom: 50px;
}

#footer .left {
	float: left;
	margin-right: 10px;
}

#footer .right {
	float: right;
}

#footer a {
	color: #999;
}
#loading {
	background-image:
		url(${pageContext.request.contextPath}/img/loading-bg.png);
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
	<div id="wrapper">
		<div id="header">
			<a id="logo" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
		</div>
		<div id="share-form">
			<form id="sharePost"
				action="${pageContext.request.contextPath}/func/share" method="POST">
				<table id="shareTable">
					<tr>
						<td colspan="2" align="left"><input class="submit" type="submit" value="게시하기" /></td>
					</tr>
					<tr>
						<td colspan="2"><textarea name="content" placeholder="공유할 내용을 적어보세요!" cols="80" rows="10"></textarea></td>
					</tr>
					<tr>
						<td  colspan="2" class="url">
							<c:choose>
								<c:when test="${param.url eq null or param.url eq '' }">공유할 주소? <input type="text" size="50" name="url"/></c:when>
								<c:otherwise>
									<input type="hidden" name="url" value="${param.url }"/>${param.url}
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr style="margin-top: 10px">
						<td width="150px"><c:if test="${maxImage ne null }"><img src="${maxImage }" width="${width }" height="${height }"></c:if></td>
						<td valign="top"><b>${title }</b><br/>${summary }</td>
					</tr>
					
				</table>
			</form>
			<table id="loading">
				<tr>
					<td align="center"><img
						src="${pageContext.request.contextPath}/img/loading-msg.png">
					</td>
				</tr>
			</table>
		</div>

		<div id="footer"></div>
	</div>

</body>
</html>