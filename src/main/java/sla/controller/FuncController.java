package sla.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sla.model.ShortUrl;
import sla.util.ShortUrlUtil;

@Controller
@RequestMapping("func")
public class FuncController {

	@RequestMapping(value = "share", method = RequestMethod.GET)
	public void share(ShortUrl shortUrl) {
		// just view
	}

	@ResponseBody
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public boolean share(@Valid ShortUrl shortUrl, BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			return false;
		} else {

			shortUrl.persist();

			shortUrl.setShortUrl(ShortUrlUtil.convert(shortUrl.getId()));
			shortUrl.merge();

			return true;
		}
	}

}
