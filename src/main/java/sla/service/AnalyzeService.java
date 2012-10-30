package sla.service;

import java.util.List;

import sla.model.ShortUrl;
import sla.model.UserInfoWithCount;
import sla.model.VisitCount;
import sla.util.ShortUrlUtil;

public class AnalyzeService {
	
	/**
	 * 
	 * @param shortUrl 분석 페이지에 해당하는 shortUrl
	 * @param startPeriod 카운트를 집계할 기간 시작 값 YYYYMMDDHH:24 ex)2012103023
	 * @param endPeriod 카운트를 집계할 기간 종료 값 YYYYMMDDHH:24 ex)2012103023
	 * @return 해당 분석페이지의 사용자별 카운트 집계
	 */
	public List<UserInfoWithCount> getCountRecordByUser(String shortUrl,int startPeriod, int endPeriod){
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		List<UserInfoWithCount> resultList=null;
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			long headId=shortUrlRecord.getHeadId();
			resultList=VisitCount.getCountRecordByUser(headId, startPeriod, endPeriod);
		}
		return resultList;
	}
}
