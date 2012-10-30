package sla.service;

import java.util.ArrayList;
import java.util.List;

import sla.model.ShortUrl;
import sla.model.UserInfoWithCount;
import sla.util.ShortUrlUtil;

public class AnalyzeService {
	public List<UserInfoWithCount> getCountRecordByUser(String shortUrl,int startPeriod, int endPeriod){
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		List<UserInfoWithCount> resultList=new ArrayList<UserInfoWithCount>();
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
		}
		return resultList;
	}
}
