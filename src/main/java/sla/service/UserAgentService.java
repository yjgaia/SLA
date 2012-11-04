package sla.service;

import org.springframework.stereotype.Service;

import sla.model.UserAgentInfo;

@Service
public class UserAgentService {
	public int increaseUseCount(long encodedKeyId,String operationSystem,String browser,String browserVersion){
		if(UserAgentInfo.existsUserAgent(encodedKeyId, operationSystem, browser, browserVersion)){ //존재 시 count++ 수행
			UserAgentInfo userAgentInfo= UserAgentInfo.getUserAgent(encodedKeyId, operationSystem, browser, browserVersion);
			userAgentInfo.increaseUseCount();
			userAgentInfo.merge();
			return userAgentInfo.getUseCount();
		}else{ //없을 시 row 추가
			UserAgentInfo userAgentInfo=new UserAgentInfo();
			userAgentInfo.setEncodedKeyId(encodedKeyId);
			userAgentInfo.setOperationSystem(operationSystem);
			userAgentInfo.setBrowser(browser);
			userAgentInfo.setBrowserVersion(browserVersion);
			userAgentInfo.setUseCount(1);
			userAgentInfo.merge();
			return userAgentInfo.getUseCount();
		}
	}
}
