package sla.model;

import javax.persistence.Column;


import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class VisitCount {
	@Column(nullable=false)
	private long encodedKeyId;
	@Column(nullable=false)
	private int timePeriod;
	
	private int visitCount;
	
	public void increaseVisitCount(){
		visitCount++;
	}
	public static boolean existsVisitCount(long encodedKeyId,int timePeriod) {
		return entityManager().createQuery("SELECT COUNT(o) FROM VisitCount o WHERE encodedKeyId = :encodedKeyId AND timePeriod =:timePeriod", Long.class).setParameter("encodedKeyId", encodedKeyId).setParameter("timePeriod", timePeriod).getSingleResult() > 0l;
	}
	public static VisitCount findVisitCountByHashedKeyAndTimePeriod(long encodedKeyId,int timePeriod) {
		
		return entityManager().createQuery("SELECT o FROM VisitCount o WHERE encodedKeyId = :encodedKeyId AND timePeriod =:timePeriod", VisitCount.class).setParameter("encodedKeyId", encodedKeyId).setParameter("timePeriod",timePeriod).getSingleResult();
	}
}
