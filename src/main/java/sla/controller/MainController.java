package sla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.data.KeyValueCache;
import sla.model.ShortUrl;
import sla.service.ShortUrlService;
import sla.service.VisitCountService;

@Controller
public class MainController {
	@Autowired
	VisitCountService visitCountService;
	@Autowired
	ShortUrlService shortUrlService;
	@Autowired
	KeyValueCache keyValueCache;
	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable String shortUrl) {
		long id=shortUrlService.getIdByShortUrl(shortUrl);
		System.out.println("얻은 id값 ?" +id);
		if(id==-1){
			return "shortUrlNotFound";
		}else{
			ShortUrl su=ShortUrl.findShortUrlById(id);
			visitCountService.increaseVisitCount(su.getId());
			String url=su.getUrl();
			return "redirect:"+url;
			
		}
	}

}
