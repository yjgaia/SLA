package sla.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sla.model.Result;
import sla.model.ShortUserInfoWithCount;
import sla.service.AnalyzeService;

@Controller
@RequestMapping("api")
public class ApiController {
	
	@Autowired
	AnalyzeService analyzeService;
	
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
	
	public String ret(Model model,Object object,boolean list) throws JsonGenerationException, JsonMappingException, IOException{
		ObjectMapper om=new ObjectMapper();
		if(list){
			model.addAttribute("result",om.writeValueAsString(new Result(object)));
		}else{
			model.addAttribute("result",om.writeValueAsString(object));
		}
		return "result";
		
	}
}
