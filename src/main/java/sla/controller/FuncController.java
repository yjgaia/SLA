package sla.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import sla.model.KeyCount;
import sla.model.Page;
import sla.model.Result;
import sla.model.ShortUrl;
import sla.model.ShortUserInfoWithCount;
import sla.model.UserInfo;
import sla.model.VisitCount;
import sla.service.AnalyzeService;
import sla.social.SocialConfig;
import sla.util.AuthUtil;
import sla.util.DateUtil;
import sla.util.ShortUrlUtil;

@Controller
@RequestMapping("func")
public class FuncController {

	@Autowired
	private AnalyzeService analyzeService;
	
	@Autowired
	private SocialConfig socialConfig;
	
	@RequestMapping("intro")
	public void intro() {
		// just view
	}
	
	@RequestMapping("button/intro")
	public void buttonIntro() {
		// just view
	}
	
	@RequestMapping("button/parameters")
	public void buttonParametersIntro() {
		// just view
	}
	
	@RequestMapping("page/create")
	public void createPage(@ModelAttribute("command") Page page) {
		// just view
	}
	
	@RequestMapping("sla/intro")
	public void slaIntro() {
		// just view
	}
	
	@RequestMapping("ckfinder")
	public void ckfinder() {
		// just view
	}
	
	@RequestMapping("analyze")
	public String analyze(@RequestParam String shortUrl,Model model) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		if(ShortUrl.existsShortUrl(id)){
			ObjectMapper objectMapper=new ObjectMapper();
			UserInfo sharer = analyzeService.getUserInfoWithShortUrl(shortUrl);
			long sharePostCount=ShortUrl.getUserSharePostCount(sharer.getId());
			List<Long> countSumBySharer=VisitCount.getCountSumByUser(sharer.getId());
			long totalCountSum=0;
			for(int i=0;i<countSumBySharer.size();i++){
				totalCountSum+=countSumBySharer.get(i);
			}
			double sharerFriendVisitorRatio;
			if(sharer.getSocialFriendCount()==0){
				sharerFriendVisitorRatio=0;
			}else{
				sharerFriendVisitorRatio=Math.floor((double)totalCountSum/((double)sharer.getSocialFriendCount())*1000)/1000;
			}
			long averageCount=totalCountSum/sharePostCount;
			List<KeyCount> genderDistribution=analyzeService.getUserGenderDistribution(shortUrl,true);
			List<KeyCount> operationSystemDistribution=analyzeService.getOperationSystemDistribution(shortUrl,true);
			List<KeyCount> browserDistribution=analyzeService.getBrowserDistribution(shortUrl,true);
			List<ShortUserInfoWithCount> countRecord=analyzeService.getCountRecordByUser(shortUrl, -1, 2013111000);
			List<KeyCount> countSum=analyzeService.getCountSumByPeriod(
					shortUrl,Integer.parseInt(DateUtil.getToday("YYYYMMDDHH")), 10,0,true);
			List<KeyCount> accumulatedCountSum=analyzeService.getAccumulatedCountSumByPeriod(shortUrl,Integer.parseInt(DateUtil.getToday("YYYYMMDDHH")), 10,0,true);
			model.addAttribute("sharer",sharer);
			model.addAttribute("sharerPostCount",sharePostCount);
			model.addAttribute("sharerTotalVisitCount",totalCountSum);
			model.addAttribute("sharerAverageVisitCount",averageCount);
			model.addAttribute("sharerFriendVisitorRatio",sharerFriendVisitorRatio);
			model.addAttribute("countRecord",objectMapper.writeValueAsString(new Result(countRecord)));
			model.addAttribute("genderDistribution",objectMapper.writeValueAsString(new Result(genderDistribution)));
			model.addAttribute("operationSystemDistribution",objectMapper.writeValueAsString(new Result(operationSystemDistribution)));
			model.addAttribute("browserDistribution",objectMapper.writeValueAsString(new Result(browserDistribution)));
			model.addAttribute("countSum",objectMapper.writeValueAsString(new Result(countSum)));
			model.addAttribute("accumulatedCountSum",objectMapper.writeValueAsString(new Result(accumulatedCountSum)));
			
			return "func/analyze";
		}else {
			return "shortUrlNotFound";
		}
	}

	@RequestMapping(value = "reShare", method = RequestMethod.GET)
	public String reShare(@RequestParam String shortUrl, Model model) {
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		System.out.println("reshareId:"+id);
		if (ShortUrl.existsShortUrl(id)) {
			ShortUrl su = ShortUrl.findShortUrl(id);
			model.addAttribute("shortUrl", su);
			return "func/reShare";
		} else {
			return "shortUrlNotFound";
		}
	}

	@Secured("ROLE_USER")
	@RequestMapping(value = "share", method = RequestMethod.GET)
	public void share() {
		// just view
	}

	/*초기 공유(마케팅 담당자)
	 * reShare=true로 들어올 시 헤당 shortUrl*/
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public boolean share(@Valid ShortUrl shortUrl,@RequestParam(required=false) boolean reShare
			,@RequestParam(required=false) String reShareShortUrl, BindingResult bindingResult,Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return false;
		} else {
			shortUrl.setUserInfo(AuthUtil.getUserInfo());
			shortUrl.persist();
			String convertedShortUrl=ShortUrlUtil.complicatedConvert(shortUrl.getId());
			shortUrl.setShortUrl(convertedShortUrl);
			 
			shortUrl.merge();
			
			
			Connection<Facebook> connection = socialConfig.connectionRepository().findPrimaryConnection(Facebook.class);
			Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate();
			
			String shortUrlString = getUrlWithContextPath(request) + "/" + shortUrl.getShortUrl();
			
			FacebookLink link = new FacebookLink(shortUrlString, null, shortUrl.getUrl(), null);
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
