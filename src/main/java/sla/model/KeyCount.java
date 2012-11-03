package sla.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class KeyCount {
	private int keyId;
	
	private String keyName;
	private int cnt;
}
