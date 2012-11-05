package sla.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import sla.model.ShortUserInfoWithCount;
import sla.model.UserInfo;
import sla.service.AnalyzeService;
import sla.util.DateUtil;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	AnalyzeService analyzeService;
	public String ret(Model model,Object object,boolean list) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper om=new ObjectMapper();
		if(list){
			model.addAttribute("result",om.writeValueAsString(new Result(object)));
		}else{
			model.addAttribute("result",om.writeValueAsString(object));
		}
		return "result";
		
	}
	
	@Secured("ROLE_USER")
	@RequestMapping("userInfo")
	public String getUserInfo(Model model,@RequestParam long id) throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo userInfo=UserInfo.findUserInfo(id);
		return ret(model,userInfo,false);
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
		return ret(model,countRecord,true);
	}
	
	@RequestMapping("countSumByPeriod")
	public String getCountSumByPeriod(Model model,@RequestParam String shortUrl,Integer endPeriod,
			@RequestParam(required=false,defaultValue="10")Integer getCount,@RequestParam(required=false,defaultValue="0") Integer gubun,@RequestParam(required=false,defaultValue="true") boolean all) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		if(endPeriod==null){
			endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDDHH"));
		}
		List<KeyCount> countSumByPeriod=analyzeService.getCountSumByPeriod(shortUrl, endPeriod, getCount, gubun, all);
		return ret(model,countSumByPeriod,true);
	}
	
	@RequestMapping("accumulatedCountSum")
	public String getAccumulatedCountSumByPeriod(Model model,@RequestParam String shortUrl,Integer endPeriod,
			@RequestParam(required=false,defaultValue="10")Integer getCount,@RequestParam(required=false,defaultValue="0") Integer gubun,@RequestParam(required=false,defaultValue="true") boolean all) throws SQLException, JsonGenerationException, JsonMappingException, IOException {
		if(endPeriod==null){
			endPeriod=Integer.valueOf(DateUtil.getToday("YYYYMMDDHH"));
		}
		List<KeyCount> accumulatedCountSum=analyzeService.getAccumulatedCountSumByPeriod(shortUrl, endPeriod, getCount, gubun, all);
		return ret(model,accumulatedCountSum,true);
	}
	@RequestMapping("sharerInfo")
	public String getUserInfoWithShortUrl(Model model, @RequestParam String shortUrl) throws JsonGenerationException, JsonMappingException, IOException{
		UserInfo sharerInfo=analyzeService.getUserInfoWithShortUrl(shortUrl);
		return ret(model,sharerInfo,false);
	}
	
	@RequestMapping("genderDistribution")
	public String getUserGenderDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> genderDist=analyzeService.getUserGenderDistribution(shortUrl, all);
		return ret(model,genderDist,true);
	}
	
	@RequestMapping("osDistribution")
	public String getOsDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> osDist=analyzeService.getOperationSystemDistribution(shortUrl, all);
		return ret(model,osDist,true);
	}
	
	@RequestMapping("browserDistribution")
	public String getBrowserDistribution(Model model,@RequestParam String shortUrl,@RequestParam(required=false,defaultValue="true") boolean all) throws JsonGenerationException, JsonMappingException, IOException, SQLException{
		List<KeyCount> browserDist=analyzeService.getBrowserDistribution(shortUrl, all);
		return ret(model,browserDist,true);
	}
	
	
}
