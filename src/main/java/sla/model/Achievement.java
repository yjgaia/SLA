package sla.model;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Achievement {
	
	public static final Achievement FIRST_LOGIN=new Achievement(0,"만나서 반갑습니다!",10,"loginCount");
	public static final Achievement[] ACHEIVES={FIRST_LOGIN};
	
	public Achievement(long id,String description,int score,String category){
		setId(id);
		this.description=description;
		this.score=score;
		this.category=category;
	}
	String name;
	String description;
	int score;
	String category;
	
	public static void addAchievementToUser(long userId,long achievementId){
		System.out.println("addAchievement:"+userId+"에 "+achievementId+"추가");
		UserAchieve userAchieve=new UserAchieve();
		userAchieve.setUserInfo(UserInfo.findUserInfo(userId));
		userAchieve.setAcheivement(findAchievement(achievementId));
		userAchieve.merge();
	}
}
