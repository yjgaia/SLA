package sla.controller;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.data.KeyValueCache;
import sla.model.ShortUrl;
import sla.model.UserInfo;
import sla.service.AchievementService;
import sla.service.UserAgentService;
import sla.service.VisitCountService;
import sla.util.AuthUtil;
import sla.util.ShortUrlUtil;

@Controller
public class MainController {
	@Autowired
	VisitCountService visitCountService;
	@Autowired
	UserAgentService userAgentService;
	@Autowired
	KeyValueCache keyValueCache;
	
	@RequestMapping("/")
	public String home(Model model)  {
		UserInfo userInfo=AuthUtil.getUserInfo();
		if(userInfo!=null){
			AchievementService.viewMain(userInfo.getId());
		}
		model.addAttribute("userCount", UserInfo.getUserCount());
		model.addAttribute("shareCount", ShortUrl.getShortUrlCount());
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable final String shortUrl,HttpServletRequest httpServletRequest) {
		final long id=ShortUrlUtil.complicatedRevert(shortUrl);
		
		final UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
		
		final String ip=httpServletRequest.getRemoteAddr();
		String storedUrl;
		if((storedUrl=keyValueCache.getStringAndResetExpireByKey(shortUrl))!=null){
			//캐싱되어있음
			Thread thread =new Thread(new Runnable() {
				
				@Override
				public void run() {
					if(ShortUrl.existsShortUrl(id)){
						final ShortUrl su=ShortUrl.findShortUrl(id);
						saveStatistics(su, ip, shortUrl, userAgent);
						
					}
				}
			});
			thread.start();
			return "redirect:"+storedUrl;
		}else{
			//캐싱안돼있음
			if(ShortUrl.existsShortUrl(id)){
				final ShortUrl su=ShortUrl.findShortUrl(id);
				Thread thread =new Thread(new Runnable() {
					
					@Override
					public void run() {
						saveStatistics(su, ip, shortUrl, userAgent);
						keyValueCache.setStringWithKey(shortUrl, su.getUrl());
					}
				});
				thread.start();
				String url=su.getUrl();
				return "redirect:"+url;
			}else{
				return "shortUrlNotFound";
			}
		}
		
	}
	public void saveStatistics(ShortUrl su,String ip,String shortUrl,UserAgent userAgent){
		if(!keyValueCache.exists(keyValueCache.generateIpKey(ip, shortUrl))){
			keyValueCache.setStringWithKey(keyValueCache.generateIpKey(ip, shortUrl), "1");
			
			if(su.getVersion()!=0){
				su.increaseVisitCountSum();
				su.merge();
	
				visitCountService.increaseVisitCount(su.getId());
			}
			
			if(userAgent!=null){
				String osName=userAgent.getOperatingSystem()==null?"":userAgent.getOperatingSystem().name();
				String browserName=userAgent.getBrowser()==null?"":userAgent.getBrowser().name();
				String browserVersion=userAgent.getBrowserVersion()==null?"":userAgent.getBrowserVersion().getVersion();
				userAgentService.increaseUseCount(su.getId(), osName, browserName, browserVersion);
			}
			
		}
	}

}
