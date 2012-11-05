<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>YOG.IO! 분석 결과 보기</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<script src="${pageContext.request.contextPath}/script/jquery-1.7.2.min.js"></script>
		<style>
			
		</style>
		<script>
		$(function() {
			
		});
		</script>
	</head>
	
	<body>
	
		분석 결과 보기<br/><br/>
		공유자 :/SLA/api/sharerInfo?shortUrl=IP<br/> ${sharer}<br/><br/>
		공유자 방문 유입 순위: /SLA/api/countRecord?shortUrl=IP<br/> ${countRecord }<br/><br/>
		성별 분포: /SLA/api/genderDistribution?shortUrl=IP<br/>${genderDistribution }<br/><br/>
		OS 분포: /SLA/api/osDistribution?shortUrl=IP<br/>${operationSystemDistribution }<br/><br/>
		브라우저 분포: /SLA/api/browserDistribution?shortUrl=IP<br/>${browserDistribution }<br/><br/>
		시간별 방문자 수 :/SLA/api/countSumByPeriod?shortUrl=IP<br/> ${countSum }<br/><br/>
		시간별 누적 방문자 수 :/SLA/api/accumulatedCountSum?shortUrl=IP<br/> ${accumulatedCountSum }
	</body>
</html>
