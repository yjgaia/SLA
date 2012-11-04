package sla.model;

import javax.persistence.Column;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserAgentInfo {
	@Column(nullable=false)
	private long encodedKeyId;
	private int useCount;
	private String operationSystem;
	private String browser;
	private String browserVersion;
	public void increaseUseCount(){
		useCount++;
	}
	
	public static boolean existsUserAgent(long encodedKeyId,String operationSystem,String browser,String browserVersion) {
		return entityManager().createQuery("SELECT COUNT(o) FROM UserAgentInfo o WHERE encodedKeyId = :encodedKeyId " +
				"AND operationSystem =:operationSystem AND browser =:browser AND browserVersion =:browserVersion ", Long.class)
				.setParameter("encodedKeyId", encodedKeyId).setParameter("operationSystem", operationSystem)
				.setParameter("browser", browser).setParameter("browserVersion", browserVersion)
				.getSingleResult() > 0l;
	}
	public static UserAgentInfo getUserAgent(long encodedKeyId,String operationSystem,String browser,String browserVersion) {
		return entityManager().createQuery("SELECT o FROM UserAgentInfo o WHERE encodedKeyId = :encodedKeyId " +
				"AND operationSystem =:operationSystem AND browser =:browser AND browserVersion =:browserVersion ", UserAgentInfo.class)
				.setParameter("encodedKeyId", encodedKeyId).setParameter("operationSystem", operationSystem)
				.setParameter("browser", browser).setParameter("browserVersion", browserVersion).getSingleResult();
	}	
}
