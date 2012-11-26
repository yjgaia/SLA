package sla.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sla.model.KeyCount;
import sla.model.Result;
import sla.model.ShortUrl;
import sla.model.ShortUserInfoWithCount;
import sla.model.UserInfo;
import sla.service.AnalyzeService;
import sla.util.DateUtil;
import sla.util.ShortUrlUtil;

@Controller
@RequestMapping("api")
public class ApiController {
	ObjectMapper om=new ObjectMapper();
	@Autowired
	AnalyzeService analyzeService;
	public String ret(Model model,Object object ) throws JsonGenerationException, JsonMappingException, IOException{
		
		
			model.addAttribute("result",om.writeValueAsString(new Result(object)));
		
		return "result";
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("userInfo")
	public String getUserInfo(Model model,@RequestParam long id) throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo userInfo=UserInfo.findUserInfo(id);
		return ret(model,userInfo);
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("userInfoAndShortUrl")
	public String userInfoAndShortUrl(Model model,@RequestParam long id,@RequestParam String shortUrl) throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo userInfo=UserInfo.findUserInfo(id);
		HashMap<String,Object> result=new HashMap<String,Object>();
		result.put("userInfo", userInfo);
		long shortUrlId=ShortUrlUtil.complicatedRevert(shortUrl);
		if(ShortUrl.existsShortUrl(shortUrlId)){
			result.put("shortUrl", ShortUrl.findShortUrl(shortUrlId));
		}
		return ret(model,result);
	}
	
	@RequestMapping("countRecord")
	public String getCountRecordByUser(Model model,@RequestParam String shortUrl,@RequestParam(required=false) Integer startPeriod,@RequestParam(required=false) Integer endPeriod) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		
		//변수 초기화
		if(startPeriod==null){
			startPeriod=-1;
		}
		if(endPeriod==null){
			endPeriod=Integer.MAX_VALUE;
		}
		List<ShortUserInfoWithCount> countRecord=analyzeService.getCountRecordByUser(shortUrl, startPeriod, endPeriod);
		return ret(model,countRecord);
	}
	
	@RequestMapping("countSumByPeriod")
	public String getCountSumByPeriod(Model model,@RequestParam String shortUrl,Integer endPeriod,
			@RequestParam(required=false,defaultValue="10")Integer getCount,@RequestParam(required=false,defaultValue="0") Integer gubun,@RequestParam(required=false,defaultValue="true") boolean all) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		if(endPeriod==null){
			switch(gubun){
			case 0:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDDHH"));
				break;
			case 1:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDD"));
				break;
			case 2:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMM"));
				break;
			}
		}
		List<KeyCount> countSumByPeriod=analyzeService.getCountSumByPeriod(shortUrl, endPeriod, getCount, gubun, all);
		return ret(model,countSumByPeriod);
	}
	
	@RequestMapping("accumulatedCountSum")
	public String getAccumulatedCountSumByPeriod(Model model,@RequestParam String shortUrl,Integer endPeriod,
			@RequestParam(required=false,defaultValue="10")Integer getCount,@RequestParam(required=false,defaultValue="0") Integer gubun,@RequestParam(required=false,defaultValue="true") boolean all) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		if(endPeriod==null){
			switch(gubun){
			case 0:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDDHH"));
				break;
			case 1:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDD"));
				break;
			case 2:
				endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMM"));
				break;
			}
			
		}
		List<KeyCount> accumulatedCountSum=analyzeService.getAccumulatedCountSumByPeriod(shortUrl, endPeriod, getCount, gubun, all);
		return ret(model,accumulatedCountSum);
	}
	@RequestMapping("sharerInfo")
	public String getUserInfoWithShortUrl(Model model, @RequestParam String shortUrl) throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo sharerInfo=analyzeService.getUserInfoWithShortUrl(shortUrl);
		return ret(model,sharerInfo);
	}
	
	@RequestMapping("genderDistribution")
	public String getUserGenderDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> genderDist=analyzeService.getUserGenderDistribution(shortUrl, all);
		return ret(model,genderDist);
	}
	
	@RequestMapping("osDistribution")
	public String getOsDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> osDist=analyzeService.getOperationSystemDistribution(shortUrl, all);
		return ret(model,osDist);
	}
	
	@RequestMapping("browserDistribution")
	public String getBrowserDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> browserDist=analyzeService.getBrowserDistribution(shortUrl, all);
		return ret(model,browserDist);
	}
	
	@RequestMapping("identifiedUserRank")
	public String getVisitRankByIdentifiedUser(Model model,@RequestParam String shortUrl) throws SQLException, JsonGenerationException, JsonMappingException, IOException{
		List<KeyCount> rank=analyzeService.getVisitRankByIdentifiedUser(shortUrl);
		return ret(model,rank);
	}
	
}
