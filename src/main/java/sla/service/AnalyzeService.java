package sla.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sla.model.ShortUrl;
import sla.model.ShortUserInfoWithCount;
import sla.model.UserInfo;
import sla.model.VisitCount;
import sla.util.ShortUrlUtil;

import com.ibatis.sqlmap.client.SqlMapClient;

@Service
public class AnalyzeService {
	
	@Autowired
	SqlMapClient sqlMapclient;
	
	/**
	 * 
	 * @param shortUrl 분석 페이지에 해당하는 shortUrl
	 * @param startPeriod 카운트를 집계할 기간 시작 값 YYYYMMDDHH:24 ex)2012103023
	 * @param endPeriod 카운트를 집계할 기간 종료 값 YYYYMMDDHH:24 ex)2012103023
	 * @return 해당 분석페이지의 사용자별 카운트 집계
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public List<ShortUserInfoWithCount> getCountRecordByUser(String shortUrl,int startPeriod, int endPeriod) throws SQLException{
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		List<ShortUserInfoWithCount> resultList=null;
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			long headId=shortUrlRecord.getHeadId();
			//resultList=VisitCount.getCountRecordByUser(headId, startPeriod, endPeriod);
			HashMap<String,Object> param=new HashMap<String,Object>();
			param.put("headId",headId);
			param.put("startPeriod",startPeriod);
			param.put("endPeriod",endPeriod);
			resultList=sqlMapclient.queryForList("Analyze.getCountRecordByUser",param);
		}
		return resultList;
	}
	public UserInfo getUserInfoWithShortUrl(String shortUrl){
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			return shortUrlRecord.getUserInfo();
		}
		return null;
	}
}
