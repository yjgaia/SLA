package sla.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
public class UserInfoWithCount {
	private UserInfo userInfo;
	private int cnt;
}
