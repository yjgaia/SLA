package sla.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Query;
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
	public static List<ShortUserInfoWithCount> getCountRecordByUser(long headId, int startPeriod, int endPeriod){
		String query="SELECT u.id,u.social_name,c.cnt AS cnt FROM (SELECT user_id, SUM(cnt) " +
				"cnt FROM(SELECT a.*, (SELECT COALESCE(SUM(visit_count),0)" +
				" FROM visit_count WHERE encoded_key_id=a.id  AND (time_period BETWEEN "+startPeriod+" AND "+endPeriod+")) " +
				"AS cnt FROM short_url a WHERE head_id="+headId+" ORDER BY cnt DESC ) count_by_user GROUP BY user_id ORDER BY cnt DESC) c" +
				",user_info u WHERE c.user_id=u.id ORDER BY c.cnt DESC";
		Query q=entityManager().createNativeQuery(query,ShortUserInfoWithCount.class);
		
		List<ShortUserInfoWithCount> a=q.getResultList();
		return a;
	}
	
	/**
	 * 유저 id를 이용해 해당 유저가 공유한 페이지의 방문자 수의 합계 리스트를 리턴해준다. (평균 방문자 수 구하는데에 사용)
	 * @param id 유저 아이디
	 * @return 해당 유저가 공유한 url의 방문자 수 합계 리스트
	 */
	public static List<Long> getCountSumByUser(long id){
		String query="SELECT SUM(visitCount) FROM VisitCount o WHERE encodedKeyId IN(SELECT id FROM ShortUrl WHERE userInfo.id=2) " +
				"GROUP BY encodedKeyId";
		
		
		TypedQuery<Long> q = entityManager().createQuery(query, Long.class);
		return q.getResultList();
	}
}
