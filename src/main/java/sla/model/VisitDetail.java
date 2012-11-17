package sla.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class VisitDetail {
	
	@ManyToOne
	@JoinColumn(name = "shortUrlId", nullable = false)
	private ShortUrl shortUrl;
	
	private String cookieId;
	
	
	private long savedUserId=0;
	
	private int visitCount=0;	
	public void increaseVisitCount(){
		visitCount++;
	}
	public static boolean existsVisitDetailByShortUrlAndCookieId(long shortUrlId,String cookieId){
		return entityManager()
				.createQuery("SELECT count(o) FROM VisitDetail o WHERE o.cookieId = :cookieId AND o.shortUrl.id=:shortUrlId",Long.class)
				.setParameter("cookieId",cookieId).setParameter("shortUrlId", shortUrlId).getSingleResult()>0l;
	}
	public static VisitDetail findVisitDetailByShortUrlAndCookieId(long shortUrlId,String cookieId){
		return entityManager()
		.createQuery("SELECT o FROM VisitDetail o WHERE o.cookieId = :cookieId AND o.shortUrl.id=:shortUrlId",VisitDetail.class)
		.setParameter("cookieId",cookieId).setParameter("shortUrlId", shortUrlId).getSingleResult();
	}
}
