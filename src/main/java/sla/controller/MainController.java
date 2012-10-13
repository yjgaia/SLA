package sla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import sla.model.ShortUrl;
import sla.service.VisitCountService;

@Controller
public class MainController {
	@Autowired
	VisitCountService visitCountService;

	@RequestMapping("/")
	public String home() {
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

}
