package sla.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
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
