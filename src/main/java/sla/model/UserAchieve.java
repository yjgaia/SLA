package sla.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserAchieve {
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name = "achievementId", nullable = false)
	private Achievement acheivement;
	
	private boolean identified=false;

}
