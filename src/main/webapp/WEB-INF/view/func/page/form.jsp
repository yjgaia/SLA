<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 페이지 만들기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
		<script src="${pageContext.request.contextPath}/script/js.js"></script>
		<style>
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
				height: 36px;
			}
			#logo {
				float: left;
			}
			#logo img {
				border: none;
			}
			#submit {
				margin-top: -2px;
				float: right;
				-webkit-appearance: none;
				-webkit-border-horizontal-spacing: 0px;
				-webkit-border-image: none;
				-webkit-border-vertical-spacing: 0px;
				-webkit-box-align: center;
				-webkit-box-shadow: white 0px 1px 0px 0px;
				background-color: #019AD2;
				background-image: -webkit-linear-gradient(top, #33BCEF, #019AD2);
				background-repeat: repeat-x;
				border-radius: .5em;
				box-shadow: white 0px 1px 0px 0px;
				box-sizing: border-box;
				cursor: pointer;
				display: block;
				float: right;
				font-size: 13px;
				font-weight: bold;
				height: 30px;
				letter-spacing: normal;
				line-height: 18px;
				margin-bottom: 0px;
				margin-left: 0px;
				margin-right: 0px;
				overflow-x: visible;
				overflow-y: visible;
				padding: .45em 2em .55em;
				position: relative;
				text-align: center;
				text-decoration: none;
				font: Arial, Helvetica, sans-serif;
				text-shadow: 0 1px 1px rgba(0,0,0,.3);
			}
			#submit {
				color: #fef4e9;
				border: solid 1px #da7c0c;
				background: #f78d1d;
				background: -webkit-gradient(linear, left top, left bottom, from(#faa51a), to(#f47a20));
				background: -moz-linear-gradient(top,  #faa51a,  #f47a20);
				filter:  progid:DXImageTransform.Microsoft.gradient(startColorstr='#faa51a', endColorstr='#f47a20');
			}
			#submit:hover {
				background: #f47c20;
				background: -webkit-gradient(linear, left top, left bottom, from(#f88e11), to(#f06015));
				background: -moz-linear-gradient(top,  #f88e11,  #f06015);
				filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f88e11', endColorstr='#f06015');
			}
			#submit:active {
				color: #fcd3a5;
				background: -webkit-gradient(linear, left top, left bottom, from(#f47a20), to(#faa51a));
				background: -moz-linear-gradient(top,  #f47a20,  #faa51a);
				filter: progid:DXImageTransform.Microsoft.gradient(startColorstr='#f47a20', endColorstr='#faa51a');
			}
			#header h3 {
				padding: 0;
				padding-top: 1px;
				margin: 0;
				margin-left: 20px;
				float: left;
			}
			#create-page-form {
				clear: both;
			}
			#footer {
				padding: 10px;
				font-size: 11px;
				text-align: center;
				color: #999;
			}
			#footer .left {
				float: left;
			}
			#footer .right {
				float: right;
			}
			#footer a {
				color: #999;
			}
		</style>
		<script>
		$(function() {
			
		});
		</script>
	</head>
	
	<body>
	
		<div id="wrapper">
			<form:form>
				<div id="header">
					<a id="logo" href="${pageContext.request.contextPath}/"><img src="${pageContext.request.contextPath}/img/logo.png"></a>
					<h3>페이지 만들기</h3>
					<input id="submit" type="submit" value="저장">
				</div>
				<div id="create-page-form">
					<form:textarea path="content" />
					<script type="text/javascript">
					var editor = CKEDITOR.replace('content', {
						enterMode : CKEDITOR.ENTER_BR,
						tabSpaces : 8,
						height: 402,
						toolbar_Custom : [
						    { name: 'document', items : ['Preview','Print','Maximize']},
						    { name: 'edit', items : ['Undo','Redo','Paste','Find','SelectAll']},
						    { name: 'sh', items : ['Code']},
						    { name: 'sum', items : ['summary']},
							{ name: 'insert', items : ['Link','Table','HorizontalRule','-','Image','Flash','Smiley','SpecialChar']},
							{ name: 'source', items : ['Source']},
							'/',
							{ name: 'styles', items : ['Font','FontSize']},
							{ name: 'basicstyles', items : ['Bold','Underline','Italic','Strike','TextColor','BGColor']},
							{ name: 'paragraph', items : ['JustifyLeft','JustifyCenter','JustifyRight','JustifyBlock','-','NumberedList','BulletedList']}
						],
						toolbar:'Custom',
						
						filebrowserBrowseUrl : '${pageContext.request.contextPath}/func/ckfinder',
						filebrowserImageBrowseUrl : '${pageContext.request.contextPath}/func/ckfinder?type=Images',
						filebrowserFlashBrowseUrl : '${pageContext.request.contextPath}/func/ckfinder?type=Flash',
						filebrowserUploadUrl : '${pageContext.request.contextPath}/func/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Files',
						filebrowserImageUploadUrl : '${pageContext.request.contextPath}/func/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Images',
						filebrowserFlashUploadUrl : '${pageContext.request.contextPath}/func/ckfinder/core/connector/java/connector.java?command=QuickUpload&type=Flash'
					});
					</script>
				</div>
			</form:form>
			<div id="footer">
				<div class="left">
					<script>YOGIO();</script>
				</div>
				<div class="right">
					&copy; <a href="http://swmaestro.kr" target="_blank">SW Maestro</a> 3rd <a href="${pageContext.request.contextPath}/func/sla/intro">SLA팀</a>
				</div>
			</div>
		</div>
		
	</body>
</html>
