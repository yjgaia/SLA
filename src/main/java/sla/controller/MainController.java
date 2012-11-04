package sla.controller;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.model.ShortUrl;
import sla.service.ShortUrlService;
import sla.service.UserAgentService;
import sla.service.VisitCountService;
import sla.util.ShortUrlUtil;

@Controller
public class MainController {
	@Autowired
	VisitCountService visitCountService;
	@Autowired
	UserAgentService userAgentService;
	@Autowired
	ShortUrlService shortUrlService;
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable String shortUrl,HttpServletRequest httpServletRequest) {
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		
		final UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
		
		System.out.println("userAgent:"+userAgent);
		if(ShortUrl.existsShortUrl(id)){
			final ShortUrl su=ShortUrl.findShortUrl(id);
			Thread thread =new Thread(new Runnable() {
				
				@Override
				public void run() {
					visitCountService.increaseVisitCount(su.getId());
					userAgentService.increaseUseCount(su.getId(), userAgent.getOperatingSystem().name(), userAgent.getBrowser().name(), userAgent.getBrowserVersion().getVersion());
				}
			});
			System.out.println("스레드 시작");
			thread.start();
			System.out.println("url이동");
			String url=su.getUrl();
			return "redirect:"+url;
		}else{
			return "shortUrlNotFound";
		}
	}

}
