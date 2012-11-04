package sla.controller;

import javax.servlet.http.HttpServletRequest;

import nl.bitwalker.useragentutils.UserAgent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.model.ShortUrl;
import sla.service.ShortUrlService;
import sla.service.VisitCountService;
import sla.util.ShortUrlUtil;

@Controller
public class MainController {
	@Autowired
	VisitCountService visitCountService;
	@Autowired
	ShortUrlService shortUrlService;
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable String shortUrl,HttpServletRequest httpServletRequest) {
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		
		UserAgent userAgent = UserAgent.parseUserAgentString(httpServletRequest.getHeader("User-Agent"));
		System.out.println("userAgent:"+userAgent);
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl su=ShortUrl.findShortUrl(id);
			visitCountService.increaseVisitCount(su.getId());
			String url=su.getUrl();
			return "redirect:"+url;
		}else{
			return "shortUrlNotFound";
		}
	}

}
