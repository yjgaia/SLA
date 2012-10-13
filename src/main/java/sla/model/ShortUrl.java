package sla.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ShortUrl {

	private String shortUrl;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;

	private String url;

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

}
