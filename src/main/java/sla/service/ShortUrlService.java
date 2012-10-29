package sla.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sla.data.KeyValueCache;
import sla.model.ShortUrl;

@Service
public class ShortUrlService {
	@Autowired
	KeyValueCache keyValueCache;
	
	//short url을 가지고 key value store에 있는지 먼저 찾고 없으면 디비에서 검색해 가져오고 key value store에 저장해준다.
	// -1 리턴시 없는 shortUrl
	public long getIdByShortUrl(String shortUrl){
		
		String valueInKeyValueStore=null;
		if(keyValueCache.exists(shortUrl)){
			valueInKeyValueStore=keyValueCache.getStringByKey(shortUrl);
		}
		if(valueInKeyValueStore==null){
			if(ShortUrl.existsShortUrl(shortUrl)){
				ShortUrl shortUrlInDB=ShortUrl.findShortUrlByShortUrl(shortUrl);
				keyValueCache.setStringWithKey(shortUrl, String.valueOf(shortUrlInDB.getId()));
				return shortUrlInDB.getId();
			}else{
				return -1;
			}
		}else{
			return Long.parseLong(valueInKeyValueStore);
		}
	}
}
