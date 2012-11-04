package sla.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sla.model.KeyCount;
import sla.model.ShortUrl;
import sla.model.ShortUserInfoWithCount;
import sla.model.UserInfo;
import sla.util.DateUtil;
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
			String url=shortUrlRecord.getUrl();
			//resultList=VisitCount.getCountRecordByUser(headId, startPeriod, endPeriod);
			HashMap<String,Object> param=new HashMap<String,Object>();
			param.put("url",url);
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
	
	@SuppressWarnings("unchecked")
	public List<KeyCount> getUserGenderDistribution(String shortUrl) throws SQLException{
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		List<KeyCount> resultList=null;
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			String[] keys={"","male","female"};
			String url=shortUrlRecord.getUrl();
			HashMap<String,Object> param=new HashMap<String,Object>();
			param.put("url",url);
			for(int i=0;i<keys.length;i++){
				param.put("key"+i, keys[i]);
			}
			resultList=sqlMapclient.queryForList("Analyze.getUserGenderDistribution",param);
		}
		
		return resultList;
	}
	
	/**
	 * 
	 * @param startPeriod 카운트를 집계할 기간 시작 값 YYYYMMDDHH:24 ex)2012103023
	 * @param endPeriod 카운트를 집계할 기간 종료 값 YYYYMMDDHH:24 ex)2012103023
	 * @param getCount endParam부터 단위간격으로 가져올 항목 숫자
	 * @param gubun 0: 시간별, 1: 날짜별, 2: 월별
	 * @return
	 * @throws SQLException 
	 */
	@SuppressWarnings("unchecked")
	public  List<KeyCount> getCountSumByPeriod(String shortUrl,int endPeriod,int getCount,int gubun,boolean all) throws SQLException{
		if(gubun==1){
			endPeriod*=100;
		}else if(gubun==2){
			endPeriod*=10000;
		}
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		List<KeyCount> sqlResultList=null;
		List<KeyCount> resultList=null;
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			
			int year=endPeriod/1000000;
			int month=(endPeriod%1000000)/10000;
			int day=(endPeriod%10000)/100;
			int hour=endPeriod%100;
			
			List<Integer> periodList=new ArrayList<Integer>();
			for(int i=getCount-1;i>-1;i--){
				Integer date=null;
				if(gubun==0){
					date=Integer.parseInt(DateUtil.getBeforeOrNextHour(year, month, day, hour, i*-1));
				}else if(gubun==1){
					date=Integer.parseInt(DateUtil.getBeforeOrNextDay(year, month, day, i*-1));
				}else if(gubun==2){
					date=Integer.parseInt(DateUtil.getBeforeOrNextMonth(year, month, i*-1));
				}
				
				periodList.add(date);
			}
			HashMap<Integer,Integer> countByPeriod=new HashMap<Integer,Integer>();
			int size=periodList.size();
			for(int i=0;i<size;i++){
				countByPeriod.put(periodList.get(i),0); //카운트 전부 0으로 초기화
			}
			
			System.out.println(periodList);
			int startPeriod;
			if(periodList.size()==0){
				startPeriod=endPeriod;
			}else{
				startPeriod=periodList.get(0);
			}
			
			HashMap<String,Object> param=new HashMap<String,Object>();
			if(all){
				param.put("url",shortUrlRecord.getUrl()); //전체 url에 대해 조회
			}else{
				param.put("encodedKeyId",shortUrlRecord.getId()); //해당 shortUrl에 대해서만 조회
			}
			param.put("startPeriod", startPeriod);
			param.put("endPeriod", endPeriod);
			if(gubun==0){
				sqlResultList=sqlMapclient.queryForList("Analyze.getCountSumByHour",param);
			}else if(gubun==1){
				sqlResultList=sqlMapclient.queryForList("Analyze.getCountSumByDay",param);
			}else if(gubun==2){
				sqlResultList=sqlMapclient.queryForList("Analyze.getCountSumByMonth",param);
			}else{
				sqlResultList=null;
			}
			size=sqlResultList.size();
			for(int i=0;i<size;i++){
				KeyCount kc=sqlResultList.get(i);
				Integer hourInt=Integer.parseInt(kc.getKey_name());
				countByPeriod.put(hourInt, kc.getCnt());
			}
			
			resultList=new ArrayList<KeyCount>();
			size=periodList.size();
			for(int i=0;i<size;i++){
				KeyCount kc=new KeyCount();
				Integer hourInt=periodList.get(i);
				kc.setKey_name(String.valueOf(hourInt));
				kc.setCnt(countByPeriod.get(hourInt));
				resultList.add(kc);
			}
		}
		return resultList;
	}
}
