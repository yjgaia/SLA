package sla.controller;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookLink;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Tweet;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
import sla.util.GetPageUtil;
import sla.util.ShortUrlUtil;
import de.l3s.boilerpipe.BoilerpipeProcessingException;
import de.l3s.boilerpipe.extractors.ArticleExtractor;
import de.l3s.boilerpipe.extractors.DefaultExtractor;

@Controller
@RequestMapping("func")
public class FuncController {

	@Autowired
	private AnalyzeService analyzeService;
	
	@Autowired
	private SocialConfig socialConfig;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
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
	
	@RequestMapping(value = "page/create", method = RequestMethod.GET)
	public void createPage(@ModelAttribute("command") Page page) {
		// just view
	}
	
	@RequestMapping(value = "page/create", method = RequestMethod.POST)
	public String createPage(@Valid @ModelAttribute("command") Page page, BindingResult bindingResult, Model model) {
		if (bindingResult.hasErrors()) {
			return "func/page/create";
		} else {
			String password = passwordEncoder.encodePassword(page.getPassword(), null);
			page.setPassword(password);
			page.setCreateDate(new Date());
			page.persist();
			return "redirect:/func/page/form?id=" + page.getId();
		}
	}
	
	@RequestMapping(value = "page/form", method = RequestMethod.GET)
	public void pageForm(@ModelAttribute("command") Page page, Model model, HttpSession session) {
		// just view
		model.addAttribute("command", Page.findPage(page.getId()));
		
		session.setAttribute("NOW_PAGE_ID", page.getId());
	}
	
	@RequestMapping(value = "page/form", method = RequestMethod.POST)
	public String pageForm(@Valid @ModelAttribute("command") Page page, BindingResult bindingResult, Model model) {
		// just view
		if (bindingResult.hasErrors()) {
			return "func/page/form";
		} else {
			Page originPage = Page.findPage(page.getId());
			
			if (!originPage.getPassword().equals(passwordEncoder.encodePassword(page.getPassword(), null))) {
				bindingResult.rejectValue("password", null, "비밀번호가 다릅니다.");
				return "func/page/form";
			}
			
			originPage.setTitle(page.getTitle());
			originPage.setContent(page.getContent());
			originPage.merge();
			
			return "redirect:/func/page/view/" + page.getId();
		}
	}
	
	@RequestMapping("page/view/{id}")
	public String createPage(@PathVariable Long id, Model model) {
		// just view
		model.addAttribute("command", Page.findPage(id));
		return "func/page/view";
	}
	
	@RequestMapping("sla/intro")
	public void slaIntro() {
		// just view
	}
	
	@RequestMapping("ckfinder")
	public void ckfinder() {
		// just view
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("myanalyze")
	public void analyzeList(Model model) {
		model.addAttribute("list", ShortUrl.findShortUrlsByUserId(AuthUtil.getUserId()));
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "delanalyze", method = RequestMethod.GET)
	public void deleteAnalyze(String shortUrl) {
		// just view
	}
	
	@Secured("ROLE_USER")
	@RequestMapping(value = "delanalyze", method = RequestMethod.POST)
	public void deleteAnalyze(String shortUrl, Model model) {
		// TODO:
	}
	
	@RequestMapping("analyze")
	public String analyze( String shortUrl,String fullUrl,Model model) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		if(shortUrl==null&&fullUrl!=null){
			shortUrl=analyzeService.getFirstShortUrlByFullUrl(fullUrl);
			if(shortUrl==null){
				return "shortUrlNotFound";
			}
		}else if(shortUrl==null&&fullUrl==null){
			return "shortUrlNotFound";
		}
		
		long id=ShortUrlUtil.complicatedRevert(shortUrl);
		if(ShortUrl.existsShortUrl(id)){
			ShortUrl shortUrlRecord=ShortUrl.findShortUrl(id);
			ObjectMapper objectMapper=new ObjectMapper();
			
			//공유자에 대한 정보
			UserInfo sharer = analyzeService.getUserInfoWithShortUrl(shortUrl);
			long sharePostCount=ShortUrl.getUserSharePostCount(sharer.getId());
			List<Long> countSumBySharer=VisitCount.getCountSumByUser(sharer.getId());
			double totalCountSum=0;
			for(int i=0;i<countSumBySharer.size();i++){
				totalCountSum+=countSumBySharer.get(i);
			}
			double sharerFriendVisitorRatio;
			double averageCount=totalCountSum/sharePostCount;
			if(sharer.getSocialFriendCount()==0){
				sharerFriendVisitorRatio=0;
			}else{
				sharerFriendVisitorRatio=Math.floor((double)averageCount/((double)sharer.getSocialFriendCount())*1000)/1000;
			}
			Integer shareRank=analyzeService.getShareRank(shortUrlRecord.getUrl(),sharer.getId());
			model.addAttribute("shortUrlRecord",shortUrlRecord);
			model.addAttribute("sharer",sharer);
			model.addAttribute("sharerPostCount",sharePostCount);
			model.addAttribute("sharerTotalVisitCount",(int)totalCountSum);
			model.addAttribute("sharerAverageVisitCount",Math.floor(averageCount*10)/10);
			model.addAttribute("sharerFriendVisitorRatio",sharerFriendVisitorRatio);
			model.addAttribute("shareRank",shareRank);
			
			//전체 url 통계에 대한 정보
			List<KeyCount> genderDistribution=analyzeService.getUserGenderDistribution(shortUrl,true);
			List<KeyCount> operationSystemDistribution=analyzeService.getOperationSystemDistribution(shortUrl,true);
			List<KeyCount> browserDistribution=analyzeService.getBrowserDistribution(shortUrl,true);
			List<ShortUserInfoWithCount> countRecord=analyzeService.getCountRecordByUser(shortUrl, -1, 2013111000);
			List<KeyCount> countSum=analyzeService.getCountSumByPeriod(
					shortUrl,Integer.parseInt(DateUtil.getToday("YYYYMMDDHH")), 10,0,true);
			List<KeyCount> accumulatedCountSum=analyzeService.getAccumulatedCountSumByPeriod(shortUrl,Integer.parseInt(DateUtil.getToday("YYYYMMDDHH")), 10,0,true);
			
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

	@Secured("ROLE_USER")
	@RequestMapping(value = "share", method = RequestMethod.GET)
	public void share(String url , Model model) throws Exception {
		if(url!=null&&!"".equals(url)){
			if(!url.contains("http://")&&!url.contains("https://")) url="http://"+url;
			
			String mainUrl=url;
			List<String > images=GetPageUtil.getImageUrls(mainUrl);
			int max=0;
			double maxWidthPixel=150;
			double maxWidth = 0;
			double maxHeight=0;
			String maxUrl="";
			for(int i=0;i<images.size();i++){
				String urlStr=images.get(i);
				if(urlStr.startsWith("/")){
					urlStr=mainUrl+urlStr;
				}
				try{
				URL urlObj = new URL(urlStr);
				
		        Image image = ImageIO.read(urlObj);
		        int width = image.getWidth(null);
		        int height = image.getHeight(null);
		        if(width*height>max){
		        	max=width*height;
		        	maxUrl=urlStr;
		        	maxWidth=width;
		        	maxHeight=height;
		        }
		        }catch(Exception e){
					System.out.println(e.toString());
				}
				
			}
			if(maxWidth>maxWidthPixel){
				maxHeight=maxHeight*(maxWidthPixel/maxWidth);
				maxWidth=maxWidthPixel;
			}
			model.addAttribute("maxImage",maxUrl);
			model.addAttribute("width",(int)maxWidth);
			model.addAttribute("height",(int)maxHeight);
			int textLimit=300;
			URL urlObj;
			urlObj = new URL(url);
			// NOTE: Use ArticleExtractor unless DefaultExtractor gives better results for you
			String text=DefaultExtractor.INSTANCE.getText(urlObj);;
			//String text = ArticleExtractor.INSTANCE.getText(urlObj);
			
			if(text.length()>textLimit){
				text=text.substring(0,textLimit)+"...";				
			}
			model.addAttribute("title",GetPageUtil.getPageTitle(urlObj));
			model.addAttribute("summary",text);
			
			   
			
		}
	}

	/*초기 공유(마케팅 담당자)
	 * reShare=true로 들어올 시 헤당 shortUrl*/
	@Secured("ROLE_USER")
	@ResponseBody
	@RequestMapping(value = "share", method = RequestMethod.POST)
	public boolean share(@Valid ShortUrl shortUrl, BindingResult bindingResult,Model model, HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return false;
		} else {
			String url=shortUrl.getUrl();
			String lastCharacter=String.valueOf(url.charAt(url.length()-1));
			if("/".equals(lastCharacter)){
				shortUrl.setUrl(url.substring(0, url.length()-1));//마지막 / 제거해줌
			}
			shortUrl.setUserInfo(AuthUtil.getUserInfo());
			shortUrl.persist();
			String convertedShortUrl=ShortUrlUtil.complicatedConvert(shortUrl.getId());
			shortUrl.setShortUrl(convertedShortUrl);
			 
			
			String socialProviderId = AuthUtil.getUserInfo().getSocialProviderId();
			if (socialProviderId != null) {
				if (socialProviderId.equals("facebook")) {
					Connection<Facebook> connection = socialConfig.connectionRepository().findPrimaryConnection(Facebook.class);
					Facebook facebook = connection != null ? connection.getApi() : new FacebookTemplate();
					
					String shortUrlString = getUrlWithContextPath(request) + "/" + shortUrl.getShortUrl();
					
					FacebookLink link = new FacebookLink(shortUrlString, null, shortUrl.getUrl(), null);
					String entityId=facebook.feedOperations().postLink(shortUrl.getContent(), link);
					shortUrl.setEntityId(entityId);
				} else if (socialProviderId.equals("twitter")) {
					Connection<Twitter> connection = socialConfig.connectionRepository().findPrimaryConnection(Twitter.class);
					Twitter twitter = connection != null ? connection.getApi() : new TwitterTemplate();
					
					String shortUrlString = getUrlWithContextPath(request) + "/" + shortUrl.getShortUrl();
					
					Tweet entity=twitter.timelineOperations().updateStatus(shortUrl.getContent() + " " + shortUrlString);
					shortUrl.setEntityId(Long.toString(entity.getId()));
				}
			}
			shortUrl.merge();
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
