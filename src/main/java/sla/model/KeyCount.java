package sla.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
public class KeyCount {
	@Id
	private int keyId;
	
	private String keyName;
	private int cnt;
}
