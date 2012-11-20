<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO!</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link href="${pageContext.request.contextPath}/style/style.css" rel="stylesheet" type="text/css" media="screen" />
		<style>
		body,p,h1,h2,h3,h4,h5,h6,ul,ol,li,dl,dt,dd,table,th,td,form,fieldset,legend,input,textarea,button,select{margin:0;padding:0;font-family:'맑은고딕',malgun gothic,'돋움',dotum, Arial, sans-serif;}
		a{
			word-wrap:break-word;
			word-break:break-all;
		}
			
			.content_in_title{
				font-size:15px;
				font-weight:bold;
				background-image:url(${pageContext.request.contextPath}/img/about_icon.jpg);
				background-repeat:no-repeat;
				background-position:left;
				padding-left:15px;
				margin-bottom:3px;
			}
			.openBtn, .layer .closeBtn {
				padding:8px 15px;border:none;border-radius:0.3em;
				background:linear-gradient(#a9a9a9,#636363);
				background:-webkit-linear-gradient(#a9a9a9,#636363);
				background:-moz-linear-gradient(#a9a9a9,#636363);
				font-family:"맑은고딕";color:white;font-weight:bold;
			}
			.layer {
				position:absolute;top:50%;left:50%;
				margin:-180px 0 0 -301px;padding:20px 10px 10px;
				width:582px;height:340px;background:white;
				text-align:center;
				z-index:20;
				border:1px solid c3c3c3;
			}
			.userLink{
				color:#3B5998;
				text-decoration: none;
				font-weight: bold;
			}
			.layer .closeBtn {margin-top:10px;}
			.dim {
				position:absolute;top:0;left:0;
				width:100%;height:100%;
				background:black;
				opacity:0.3;z-index:10;
			}		
			th{
				text-align: left;
			}
		</style>
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<script src="${pageContext.request.contextPath}/script/highchart/highcharts.js"></script>
		<script src="${pageContext.request.contextPath}/script/highchart/modules/exporting.js"></script>	
		<script type="text/javascript">
		$(function () {
			var chart, chart2, chart3, chart4, chart5,chart6, chart7;
			var $layer = $(".layer");
			var wrapHeight = $(document).height();
			$layer.hide();
			$(".closeBtn").click(function(){
				$(".dim").remove();
				$layer.hide();
			});
			$(".dim").click(function(){
				$(".dim").remove();
				$layer.hide();
			});

			$(document).ready(function() {
				
				
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'chart1',
						type: 'line'
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},yAxis: {
						tickInterval:5,
						title: {
							text: ''
						}
					},series: [{
						name: '',
						showInLegend: false
					}]
				});

				chart3 = new Highcharts.Chart({
					chart: {
						renderTo: 'chart3',
						type: 'line'
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},yAxis: {
						tickInterval:5,
						title: {
							text: ''
						}
					},
					series: [{
						name: 'Tokyo',
						showInLegend: false
					}]
				});

				chart4 = new Highcharts.Chart({
					chart: {
						renderTo: 'chart4',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled:false,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
								}
							}
						}
					},
					series: [{
						type: 'pie',
						name: '비율'
					}]
				});

				chart5 = new Highcharts.Chart({
					chart: {
						renderTo: 'chart5',
						type: 'bar'
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},
					xAxis: {
						labels: {
						formatter: function() {
							//console.log(this.value);
							var obj=this.value;
							if(obj==null){ 
								return '<table><tr><td valign="center"><IMG src="" width="25" height="25"></td><td valign="center">null</td>';
							}else{
								
								
								return '<table><tr><td valign="center">'+obj.name+'</td><td valign="center"><IMG src="'+obj.social_url+'" width="25" height="25"></td></tr></table>';
							}
							
							
						},
						useHTML: true

					}
					},yAxis: {
						title: {
							text: ''
						}
					},
					series: [{
						showInLegend: false
					}]
				});
				
				//for os distribution
				chart6 = new Highcharts.Chart({
					chart: {
						renderTo: 'chart6',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled:false,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
								}
							}
						}
					},
					series: [{
						type: 'pie',
						name: '비율'
					}]
				});
				
				//for browser distribution
				chart7 = new Highcharts.Chart({
					chart: {
						renderTo: 'chart7',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					credits: {
						enabled:false
					},
					title: {
						text: ''
					},exporting: {
						enabled: false
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled:false,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
								}
							}
						}
					},
					series: [{
						type: 'pie',
						name: '비율'
					}]
				});
				
				var series = {
						id: 'series',
						name: '방문자 수',
						showInLegend: false,
						data: []
					};

				var category = [];
				var datas=${accumulatedCountSum };	
				for (var i=0; i<datas.data.length;i++)
				{
					category[i] = datas.data[i].key_name.substr(8,2)+'시';
					
					series.data.push([
						datas.data[i].key_name,
						datas.data[i].cnt
					]);
				}
					
				chart.xAxis[0].setCategories(category);
				chart.addSeries(series);

				var series = {
						id: 'series',
						name: '방문자 수',
						showInLegend: false,
						data: []
					};

				var category = [];
				var datas=${countSum };	
				for (var i=0; i<datas.data.length;i++)
				{
					category[i] = datas.data[i].key_name.substr(8,2)+'시';
					
					series.data.push([
						datas.data[i].key_name,
						datas.data[i].cnt
					]);
				}
					
				chart3.xAxis[0].setCategories(category);
				chart3.addSeries(series);

				datas=${countRecord };
				console.log(datas);
				var series = {
						id: 'series',
						name: '방문자 수',
						showInLegend: false,
						data: [],						
						events: {
						  click: function(event) {
							  $("body").append("<div class='dim'></div>");
							  $(".dim").css("height", wrapHeight);
							  
							  $layer.show();
							  
							  //id 다음부분에 user.id 가 들어가도록 해줄 것
							  $.getJSON('${pageContext.request.contextPath}/api/userInfoAndShortUrl?id='+event.point.id+'&shortUrl=${param.shortUrl}', function(datas) {
								  var dat = datas.data;
								  
								 $("div#popup_prop1").text(dat.userInfo.socialName);
								 $("div#popup_prop2").text(dat.userInfo.socialFriendCount);
								 if(dat.userInfo.socialGender == "male")
								 	$("div#popup_prop3").text("남");
								 else
									$("div#popup_prop3").text("여");
								 
								 $("div#popup_prop4").text(dat.userInfo.socialBirthday);
								 $("div#popup_prop5").text(dat.userInfo.socialEmail);
								 
								 var obj = 	$.parseJSON(dat.shortUrl.comments);
								 console.log(dat);
								 console.log(obj);
								 
								 if(dat.shortUrl.likeCount > 0)
								 {
									 $("div#likes_count").text(dat.shortUrl.likeCount+"명이 좋아합니다.")
								 }
								 
								if(dat.shortUrl.comments != "")
								{
									var tableString = "<html><head></head><body><table>"
									for(i=0; i<obj.length; i++)
									{
										tableString += "<tr><td><img src='http://graph.facebook.com/"+obj[i].from.id+"/picture' width='25' height='25'></td><td><font size='2'>"+obj[i].from.name+"</font></td><td><font size='2'>"+obj[i].message+"</font></td></tr>"
									}
									tableString += "</table></body></html>";
									$("#reply").contents().find('html').html(tableString);
								}
							  });
						  }
						}
				};

				var category = [];
					
				for (var i=0; i<datas.data.length;i++)
				{
					category.push({"name":datas.data[i].social_name,"social_url":datas.data[i].social_image_url});
					series.data.push({"id":datas.data[i].id,"name":datas.data[i].social_name,"y":datas.data[i].cnt,"color": '#0074A6'});
				}
				chart5.xAxis[0].setCategories(category);
				chart5.addSeries(series);
				

				var series = {
						id: 'series',
						name: '방문자',
						type: 'pie',
						showInLegend: true,
						data: []
					};
				datas=${genderDistribution}; // 데이터 삽입
				for (var i=0; i<datas.data.length;i++)
				{
					if(datas.data[i].key_name == "female")
					{
						series.data.push([
							"여",
							datas.data[i].cnt
						]);
					}else if(datas.data[i].key_name == "male"){
						series.data.push([
							"남",
							datas.data[i].cnt
						]);
					}else{
						series.data.push([
								"알 수 없음",
								datas.data[i].cnt
						]);
					}
				}
	
					
				chart4.addSeries(series);
				
				series = {
						id: 'series',
						name: '방문자',
						type: 'pie',
						showInLegend: true,
						data: []
					};
				datas=${operationSystemDistribution}; // 데이터 삽입
				for (var i=0; i<datas.data.length;i++)
				{
					series.data.push([
										datas.data[i].key_name,
										datas.data[i].cnt
								]);
					
				}
	
					
				chart6.addSeries(series);
				
				series = {
						id: 'series',
						name: '방문자',
						type: 'pie',
						showInLegend: true,
						data: []
					};
				datas=${browserDistribution}; // 데이터 삽입
				for (var i=0; i<datas.data.length;i++)
				{
					series.data.push([
										datas.data[i].key_name,
										datas.data[i].cnt
								]);
					
				}
	
					
				chart7.addSeries(series);
				
			}); //end of function
				

			$("input[name=radio]").change(function () {
				if($("input[name='radio']:checked").val() == 'a')
				{
					$.getJSON('${pageContext.request.contextPath}/api/accumulatedCountSum?shortUrl=${param.shortUrl}&gubun=0', function(datas) {
						var series = {
							id: 'series',
							name: '방문자수',
							showInLegend: false,
							data: []
						}

						var category = [];
						
						for (var i=0; i<datas.data.length;i++)
						{
							category[i] = datas.data[i].key_name.substr(8,2)+'시';
							series.data.push([
								datas.data[i].key_name,
								datas.data[i].cnt
							]);
						}
						
						
						while(chart.series.length > 0)
							chart.series[0].remove(true);
						chart.xAxis[0].setCategories(category);
						chart.addSeries(series);
						
					});
				}else if($("input[name='radio']:checked").val() == 'b'){
					
					$.getJSON('${pageContext.request.contextPath}/api/accumulatedCountSum?shortUrl=${param.shortUrl}&gubun=1', function(datas) {
					var series = {
						id: 'series',
						name: '방문자수',
						showInLegend: false,
						data: []
					}

					var category = [];
					
					for (var i=0; i<datas.data.length;i++)
					{
						category[i] = datas.data[i].key_name.substr(6,2)+'일';
						series.data.push([
							datas.data[i].key_name,
							datas.data[i].cnt
						]);
					}
					
					while(chart.series.length > 0)
						chart.series[0].remove(true);
					chart.xAxis[0].setCategories(category);
					chart.addSeries(series);
				});
				}else if($("input[name='radio']:checked").val() == 'c'){
					
					$.getJSON('${pageContext.request.contextPath}/api/accumulatedCountSum?shortUrl=${param.shortUrl}&gubun=2', function(datas) {
					var series = {
						id: 'series',
						name: '방문자수',
						showInLegend: false,
						data: []
					}

					var category = [];
					
					for (var i=0; i<datas.data.length;i++)
					{
						category[i] = datas.data[i].key_name.substr(4,2)+'월';
						series.data.push([
							datas.data[i].key_name,
							datas.data[i].cnt
						]);
					}
					
					while(chart.series.length > 0)
						chart.series[0].remove(true);
					chart.xAxis[0].setCategories(category);
					chart.addSeries(series);
				});
				}

			});
			
		});
		</script>
	</head>
	<body>
	<div class="layer">
			<table width="100%">
				<tr><th>이름</th><td><div id="popup_prop1"></div></td></tr>
				<tr><th>친구 수</th><td><div id="popup_prop2"></div></td></tr>
				<tr><th>성별</th><td><div id="popup_prop3"></div></td></tr>
				<tr><th>생일</th><td><div id="popup_prop4"></div></td></tr>
				<tr><th>이메일</th><td><div id="popup_prop5"></div></td></tr>
				<tr><td colspan="2"><div id="likes_count"></div></td></tr>
				<tr><td colspan="2"><iframe style="border:0px;" id="reply" width="100%" height="150"></iframe></td></tr>
			</table>
		<button type="button" class="closeBtn">창 닫기</button>
	</div>
		<div id="menu-wrapper">
		<div id="menu">
			<div style="display:table-cell;float:left;padding:5px 10px 0px 10px;">
				<a href="${pageContext.request.contextPath}/"><img src = "${pageContext.request.contextPath}/img/logo_small.png" border="0"></a>
			</div>
			<div id="menuin" style="display:table-cell">
			<ul>
				<li><a href="${pageContext.request.contextPath}/">홈</a></li>
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
		<h3 style="text-align:center;font-weight: normal;"><b><a class="userLink" href="${shortUrlRecord.url }" target="_blank">${shortUrlRecord.url }</a></b><br/>의 공유 효과</h3>
		<div id="main-row1">
			<div style="width: 550px;float:left;position:relative;">
				<div class="content_in_title">누적 방문자</div>
			</div>
			<div style="width: 150px;float:right;position:relative;">
				<font size="2">
				<input type="radio" name="radio" checked value="a">시간</input>
				<input type="radio" name="radio" value="b">일</input>
				<input type="radio" name="radio" value="c">월</input>
				</font>
			</div>
			<div id="chart1" style="min-width: 600px; height: 350px; margin: 0 auto"></div>
		</div>
		<div id="main-row1-spacing" style="height:40px;"></div>
		<div id="main-row2">
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">이 주소의 공유자는?</div>
			</div>
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">시간 별 방문자</div>
			</div>
			
			<div style="width: 350px; height: 200px;float:left;position:relative;">
			<table style="text-align: right">
				<tr><td colspan=2>이 주소의 <b>${shareRank }번째</b> 공유자 입니다!</td></tr>
				<tr><th><img src="${sharer.socialImageUrl }"></th>
				<td><a class="userLink" href="${sharer.socialProfileUrl }" target="_blank">${sharer.socialName }</a></td></tr>
				<tr><th>친구 수</th><td>${sharer.socialFriendCount }명</td></tr>
				<tr><th>공유 글 수</th><td>${sharerPostCount }건</td></tr>
				<tr><th>누적 방문자 수</th><td>${sharerTotalVisitCount }명</td></tr>
				<tr><th>평균 방문자 수</th><td>${sharerAverageVisitCount }명</td></tr>
				<tr><th>평균 방문자/친구 비율</th><td>${sharerFriendVisitorRatio }</td></tr>
			</table>
			</div>
			<div id="chart3" style="width: 350px; height: 200px;float:left;position:relative;"></div>
		</div>
		<div id="main-row2-spacing" style="height:20px;"></div>
		<div id="main-row3">
			
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">공유자들의 성별</div>
			</div>
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">공유 영향력</div>
			</div>

			<div id="chart4" style="width: 350px; height: 200px;float:left;position:relative;"></div>
			<div id="chart5" style="width: 350px; height: 200px;float:left;position:relative;"></div>
			
		</div>
		<div id="main-row4">
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">방문자들이 사용중인 OS</div>
			</div>
			<div style="width: 350px;float:left;position:relative;">
				<div class="content_in_title">방문자들이 사용중인 Browser</div>
			</div>
			<div id="chart6" style="width: 350px; height: 200px;float:left;position:relative;"></div>
			<div id="chart7" style="width: 350px; height: 200px;float:left;position:relative;"></div>
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