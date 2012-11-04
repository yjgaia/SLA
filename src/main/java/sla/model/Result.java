package sla.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class Result {
	public Result(Object data){
		this.data=data;
	}
	Object data;
}
