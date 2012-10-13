package sla.model;

import java.util.Date;

import javax.persistence.Column;


import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class VisitCount {
	@Column(nullable=false)
	private String hashedKey;
	@Column(nullable=false)
	private String timePeriod;
	
	private int visitCount;
	
	public void increaseVisitCount(){
		visitCount++;
	}
	public static boolean existsVisitCount(String hashedKey,String timePeriod) {
		return entityManager().createQuery("SELECT COUNT(o) FROM VisitCount o WHERE hashedKey = :hashedKey AND timePeriod =:timePeriod", Long.class).setParameter("hashedKey", hashedKey).setParameter("timePeriod", timePeriod).getSingleResult() > 0l;
	}
	public static VisitCount findVisitCountByHashedKeyAndTimePeriod(String hashedKey,String timePeriod) {
		
		return entityManager().createQuery("SELECT o FROM VisitCount o WHERE hashedKey = :hashedKey AND timePeriod =:timePeriod", VisitCount.class).setParameter("hashedKey", hashedKey).setParameter("timePeriod",timePeriod).getSingleResult();
	}
}
