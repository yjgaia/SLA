package sla.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sla.model.ShortUrl;
import sla.model.UserInfo;
import sla.util.ShortUrlUtil;

@Controller
@RequestMapping("func")
public class FuncController {

	
	@RequestMapping(value = "reShare", method = RequestMethod.GET)
	public String reShare(String shortUrl,Model model) {
		// just view
		if(ShortUrl.existsShortUrl(shortUrl)){
			ShortUrl su=ShortUrl.findShortUrlByShortUrl(shortUrl);
			System.out.println(su.toString());
			model.addAttribute("shortUrl",su);
			return "func/reShare";
		}else{
			return "shortUrlNotFound";
		}
	}
	@RequestMapping(value="share", method=RequestMethod.GET)
	public String share(){
		return "func/share";
	}

	@ResponseBody
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public boolean share(@Valid ShortUrl shortUrl,long id, BindingResult bindingResult,
			Model model) {
		
		//url에 대해서 http://를 포함 했는지 체크 후 추가 시켜 줘야함
		if (bindingResult.hasErrors()) {
			return false;
		} else {
			shortUrl.setUserInfo(UserInfo.findUserInfo(id));
			shortUrl.persist();

			shortUrl.setShortUrl(ShortUrlUtil.convert(shortUrl.getId()));
			shortUrl.merge();
			
			

			return true;
		}
	}

}
