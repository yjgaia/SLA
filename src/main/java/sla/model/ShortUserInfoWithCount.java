package sla.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@Entity
public class ShortUserInfoWithCount   {
	@Id
	private int id;
	private String socialName;
	private int cnt;
}
