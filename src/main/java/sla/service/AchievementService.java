package sla.service;

import sla.model.Achievement;

public class AchievementService {
	public static void loginCountAchievement(long userId,int loginCount){
		if(loginCount>=10&&loginCount<25){
			Achievement.addAchievementToUser(userId, Achievement.get("login_10"));
		}else if(loginCount>=25&&loginCount<50){
			Achievement.addAchievementToUser(userId, Achievement.get("login_25"));
		}else if(loginCount>=50&&loginCount<100){
			Achievement.addAchievementToUser(userId, Achievement.get("login_50"));
		}else if(loginCount>=100){
			Achievement.addAchievementToUser(userId, Achievement.get("login_100"));
		}
	}
}
