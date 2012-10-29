package sla.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sla.data.KeyValueCache;
import sla.model.ShortUrl;
import sla.social.SocialConfig;
import sla.util.AuthUtil;
import sla.util.ShortUrlUtil;

@Controller
@RequestMapping("func")
public class FuncController {

	
	@Autowired
	private SocialConfig socialConfig;
	
	@Autowired
	private KeyValueCache keyValueCache;

	@RequestMapping(value = "reShare", method = RequestMethod.GET)
	public String reShare(String shortUrl, Model model) {
		// just view
		/*if (ShortUrl.existsShortUrl(shortUrl)) {
			ShortUrl su = ShortUrl.findShortUrlByShortUrl(shortUrl);
			System.out.println(su.toString());
			model.addAttribute("shortUrl", su);
			return "func/reShare";
		} else {
			return "shortUrlNotFound";
		}*/
		return "shortUrlNotFound";
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "share", method = RequestMethod.GET)
	public void share() {
		// just view
	}

	/*초기 공유(마케팅 담당자)*/
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public boolean share(@Valid ShortUrl shortUrl, BindingResult bindingResult,
			Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return false;
		} else {
			//long randomKey=(long)Math.floor(Math.random()*10000);
			shortUrl.setUserInfo(AuthUtil.getUserInfo());
			shortUrl.persist();
			String convertedShortUrl=ShortUrlUtil.complicatedConvert(shortUrl.getId());
			shortUrl.setShortUrl(convertedShortUrl);
			shortUrl.setHeadId(shortUrl.getId()); //초기 공유의 경우 head_id를 현재 공유 정보의 id로 설정 
			shortUrl.merge();
			//Redis에 shortUrl을 key로 id를 value로 저장해둠 ( db hit를 줄이기 위함 + where절 검색을 줄임)
			//keyValueCache.setStringWithKey(convertedShortUrl, String.valueOf(shortUrl.getId())); 
			
			
			Connection<Facebook> connection = socialConfig.connectionRepository().findPrimaryConnection(Facebook.class);
			Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate();
			
			FacebookLink link = new FacebookLink(getUrlWithContextPath(request) + "/" + shortUrl.getShortUrl(), 
			       null, shortUrl.getUrl(), null);
			facebook.feedOperations().postLink(shortUrl.getContent(), link);
			
			return true;
		}
	}

	private static String getUrlWithContextPath(HttpServletRequest request) {
		return request.getScheme() + "://" + request.getServerName() + ":"
				+ request.getServerPort() + request.getContextPath();
	}

	@RequestMapping(value = "signin", method = RequestMethod.GET)
	public void signin() {
		// just view
	}

}
