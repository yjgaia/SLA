package sla.model;


import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class ShortUserInfoWithCount   {
	private int id;
	private String social_name;
	private String social_image_url;
	private int cnt;
	private String short_url;
}
