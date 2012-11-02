package sla.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class ShortUrl {

	@Column(nullable=false)
	private long headId;
	
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
	
	public static Long getUserSharePostCount(long userId){
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM ShortUrl o WHERE userInfo.id = :userId",
						Long.class).setParameter("userId", userId)
				.getSingleResult();
	}
	
	public static List<KeyCount> getUserGenderDistribution(long headId){
		String[] key={"","male","female"};
		String query="SELECT 1 as keyId,socialGender as keyName,count(o) as cnt FROM UserInfo o " +
				"where id in (SELECT userInfo.id FROM ShortUrl where headId=1) " +
				"GROUP BY socialGender";
		query="SELECT key_id,key_name,COALESCE(cnt,0) as cnt FROM " +
				"(select 1 as key_id,:key1 as key_name from dual union select 2 as key_id,:key2 as key_name from dual union select 3 as key_id,:key3 as key_name from dual) a" +
				" left join (SELECT social_gender as key_name,count(*) as cnt FROM user_info where id in " +
				"(SELECT user_id FROM short_url where head_id=:headId) GROUP BY social_gender) b using(key_name)";
		Query q=entityManager().createNativeQuery(query, KeyCount.class);
		q.setParameter("key1", key[0]);
		q.setParameter("key2", key[1]);
		q.setParameter("key3", key[2]);
		q.setParameter("headId",headId);
		return q.getResultList();
	}

}
