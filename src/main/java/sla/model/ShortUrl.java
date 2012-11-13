package sla.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ShortUrl {

	
	@Column(unique = true)
	private String shortUrl;
	
	private String entityId;
	
	private Integer visitCountSum=0;
	public Integer increaseVisitCountSum(){
		return visitCountSum++;
	}
	@Lob
	@Size
	@Column
	private String comments;
	
	@Lob
	@Size
	@Column
	private String likes;
	private Integer likeCount=0;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;

	@NotEmpty
	@Column
	private String url;

	public void setUrl(String url) {
		if (!url.contains("http://")) {
			url = "http://" + url;
		}
		this.url = url;
	}

	@Size(max = 3000)
	@Column(length = 3000)
	private String content;
	
	private boolean hide = false;

	public static Long getShortUrlCount(){
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM ShortUrl o",
						Long.class).getSingleResult();
	}
	public static boolean existsShortUrl(long id) {
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM ShortUrl o WHERE o.id = :id",
						Long.class).setParameter("id", id)
				.getSingleResult() > 0l;
	}
	public static boolean existsShortUrl(String shortUrl) {
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM ShortUrl o WHERE o.shortUrl = :shortUrl",
						Long.class).setParameter("shortUrl", shortUrl)
				.getSingleResult() > 0l;
	}
	

	public static ShortUrl findShortUrlByShortUrl(String shortUrl) {
		
		return entityManager()
				.createQuery(
						"SELECT o FROM ShortUrl o WHERE o.shortUrl = :shortUrl",
						ShortUrl.class).setParameter("shortUrl", shortUrl)
				.getSingleResult();
	}
	public static ShortUrl findShortUrlByUserAndShortUrl(long userId,String url) {
		
		return entityManager()
				.createQuery(
						"SELECT o FROM ShortUrl o WHERE o.url = :url AND o.userInfo.id=:userId",
						ShortUrl.class).setParameter("url", url).setParameter("userId", userId)
				.getSingleResult();
	}
	
	public static List<ShortUrl> findShortUrlsByUserId(Long userId) {
		
		return entityManager()
				.createQuery(
						"SELECT o FROM ShortUrl o WHERE o.hide != true AND o.userInfo.id = :userId",
						ShortUrl.class).setParameter("userId", userId)
				.getResultList();
	}
	
	public static List<ShortUrl> findAllShortUrls() {
		
		return entityManager()
				.createQuery(
						"SELECT o FROM ShortUrl o WHERE o.hide != true",
						ShortUrl.class)
				.getResultList();
	}
	
	
	public static Long getUserSharePostCount(long userId){
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM ShortUrl o WHERE userInfo.id = :userId",
						Long.class).setParameter("userId", userId)
				.getSingleResult();
	}
	

}
