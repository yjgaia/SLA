package sla.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nl.bitwalker.useragentutils.UserAgent;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.data.KeyValueCache;
import sla.model.ShortUrl;
import sla.model.UserInfo;
import sla.model.VisitDetail;
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
	public String home(Model model,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResonse) throws JsonGenerationException, JsonMappingException, IOException, SQLException  {
	
		UserInfo userInfo=AuthUtil.getUserInfo();
		Cookie cookie=checkAndGetUserIdentifyCookie(httpServletRequest.getCookies(),httpServletResonse);
		if(userInfo!=null){
			AchievementService.viewMain(userInfo.getId());
			visitCountService.updateSavedUserIdByLoggedInUser(userInfo.getId(),cookie.getValue());
		}
		model.addAttribute("userCount", UserInfo.getUserCount());
		model.addAttribute("shareCount", ShortUrl.getShortUrlCount());
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable final String shortUrl,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResonse) throws JsonGenerationException, JsonMappingException, IOException {
		//Cookie 체크
		
		final Cookie cookie=checkAndGetUserIdentifyCookie(httpServletRequest.getCookies(),httpServletResonse);
		
		
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
						saveStatistics(su, ip, shortUrl, userAgent,cookie);
						
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
						saveStatistics(su, ip, shortUrl, userAgent,cookie);
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
	private Cookie checkAndGetUserIdentifyCookie(Cookie[] cookies,HttpServletResponse response) throws JsonGenerationException, JsonMappingException, IOException {
		Cookie result=null;
		String USER_INDENTIFIER="user_indentifier";
		ObjectMapper obj=new ObjectMapper();
		if(cookies!=null){
			for(int i=0;i<cookies.length;i++){
				Cookie temp=cookies[i];
				if(USER_INDENTIFIER.equals(temp.getName())){
					System.out.println("유저가 갖고 있는 쿠키:"+obj.writeValueAsString(temp));
					result=temp;
				}
			}
		}
		if(result==null){
			Date date=new Date();
			Cookie newCookie=new Cookie(USER_INDENTIFIER,ShortUrlUtil.convert(date.getTime()));
			response.addCookie(newCookie);
			System.out.println("쿠키 생성:"+obj.writeValueAsString(newCookie));
			result=newCookie;
		}
		return result;
	}

	public void saveStatistics(ShortUrl su,String ip,String shortUrl,UserAgent userAgent,Cookie cookie){
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
		VisitDetail visitDetail=null;
		if(VisitDetail.existsVisitDetailByShortUrlAndCookieId(su.getId(), cookie.getValue())){
			visitDetail=VisitDetail.findVisitDetailByShortUrlAndCookieId(su.getId(), cookie.getValue());
		}else{
			visitDetail=new VisitDetail();
			visitDetail.setShortUrl(su);
			visitDetail.setCookieId(cookie.getValue());
			
		}
		if(visitDetail!=null){
			visitDetail.increaseVisitCount();
			visitDetail.merge();
		}
	}

}
