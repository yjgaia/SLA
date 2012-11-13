package sla.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

import sla.model.ShortUrl;
import sla.model.VisitCount;

@Service
public class VisitCountService {
	public int increaseVisitCount(long encodedKeyId){
		Calendar nowDate=Calendar.getInstance();
		String nowTimePeriodString=getTimePeriod(nowDate);
		int nowTimePeriod=Integer.parseInt(nowTimePeriodString);
		
		if(VisitCount.existsVisitCount(encodedKeyId, nowTimePeriod)){ //존재 시 count++ 수행
			VisitCount visitCount= VisitCount.findVisitCountByHashedKeyAndTimePeriod(encodedKeyId,nowTimePeriod);
			visitCount.increaseVisitCount();
			visitCount.merge();
			return visitCount.getVisitCount();
		}else{ //없을 시 row 추가
			VisitCount visitCount=new VisitCount();
			visitCount.setEncodedKeyId(encodedKeyId);
			visitCount.setTimePeriod(nowTimePeriod);
			visitCount.setVisitCount(1);
			visitCount.merge();
			return visitCount.getVisitCount();
		}
	}
	
	public String getTimePeriod(Calendar calendar){
		int year=calendar.get(1);
		int month=calendar.get(2)+1; //월은 0부터 시작
		int day=calendar.get(5);
		int hour=calendar.get(11);
		
		return year+intToString(month)+intToString(day)+intToString(hour);
	}
	
	public static String intToString(int num){
		if(num<10){
			return "0"+num;
		}else{
			return String.valueOf(num);
		}
	}
}
