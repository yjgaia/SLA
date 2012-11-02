package sla.model;

import javax.persistence.Entity;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class ShortUserInfoWithCount   {
	private int id;
	private String socialName;
	private int cnt;
}
