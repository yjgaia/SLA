package sla.model;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class UserAchieve {
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name = "achievementId", nullable = false)
	private Achievement achievement;
	
	
	private boolean identified=false;
	
	public static boolean existsAchieve(long userId,Achievement achievement) {
		return entityManager()
				.createQuery(
						"SELECT COUNT(o) FROM UserAchieve o WHERE o.userInfo.id = :userId and o.achievement.id= :achievementId",
						Long.class).setParameter("userId", userId).setParameter("achievementId", achievement.getId())
				.getSingleResult() > 0l;
	}

}
