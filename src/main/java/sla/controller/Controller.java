package sla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import sla.model.ShortUrl;
import sla.service.VisitCountService;

@org.springframework.stereotype.Controller
public class Controller {
	@Autowired
	VisitCountService visitCountService;

	@RequestMapping("/")
	public String home() {
		// System.out.println(visitCountService
		// .increaseVisitCount("oksktank/http://naver.com/1"));
		return "home";
	}

	@RequestMapping("/{shortUrl}")
	public String shortUrl(@PathVariable String shortUrl) {

		if (ShortUrl.existsShortUrl(shortUrl)) {
			System.out.println(ShortUrl.findShortUrlByShortUrl(shortUrl));
			return "redirect:";
		} else {
			return "shortUrlNotFound";
		}
	}

	@RequestMapping(value = "/func/share", method = RequestMethod.GET)
	public String share(ShortUrl shortUrl) {
		return "share";
	}

}
