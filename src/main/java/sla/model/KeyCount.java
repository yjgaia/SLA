package sla.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class KeyCount {
	private int key_id;
	
	private String key_name;
	private int cnt;
}
