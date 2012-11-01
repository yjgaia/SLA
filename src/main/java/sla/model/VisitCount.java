package sla.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.TypedQuery;

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
	public static List<UserInfoWithCount> getCountRecordByUser(long id, int startPeriod, int endPeriod){
		String query =
				"SELECT c.*,u.* FROM " +
					"(SELECT user_id, SUM(cnt) cnt FROM " +
						"(SELECT a.*, " +
							"(SELECT COALESCE(SUM(visit_count),0) FROM visit_count WHERE encoded_key_id=a.id  AND (time_period BETWEEN 2012103002 AND 2012103003)) AS cnt " +
						"FROM short_url a WHERE head_id=5 ORDER BY cnt DESC ) count_by_user " +
					"GROUP BY user_id ORDER BY cnt DESC) c, " +
				"user_info u WHERE c.user_id=u.id ORDER BY cnt DESC";
		
		TypedQuery<UserInfoWithCount> q = entityManager().createQuery(query, UserInfoWithCount.class);
		return null;
	}
}
