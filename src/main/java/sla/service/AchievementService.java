package sla.service;

import sla.model.Achievement;

public class AchievementService {
	public static void loginCountAchievement(long userId,int loginCount){
		if(loginCount>=10&&loginCount<25){
			Achievement.addAchievementToUser(userId, Achievement.get("login_10"));
		}else if(loginCount==25&&loginCount<50){
			Achievement.addAchievementToUser(userId, Achievement.get("login_25"));
		}else if(loginCount==50&&loginCount<100){
			Achievement.addAchievementToUser(userId, Achievement.get("login_50"));
		}else if(loginCount==100){
			Achievement.addAchievementToUser(userId, Achievement.get("login_100"));
		}
	}

	public static void shareCountAchievement(Long userId, long shareCount) {
		if(shareCount==1&&shareCount<10){
			Achievement.addAchievementToUser(userId, Achievement.get("first_share"));
		}else if(shareCount==10&&shareCount<25){
			Achievement.addAchievementToUser(userId, Achievement.get("share_10"));
		}else if(shareCount==25&&shareCount<50){
			Achievement.addAchievementToUser(userId, Achievement.get("share_25"));
		}else if(shareCount==50&&shareCount<100){
			Achievement.addAchievementToUser(userId, Achievement.get("share_50"));
		}else if(shareCount==100){
			Achievement.addAchievementToUser(userId, Achievement.get("share_100"));
		}
	}

	public static void viewIntro(Long id) {
		Achievement.addAchievementToUser(id, Achievement.get("view_intro"));
	}
	public static void viewMain(Long id) {
		Achievement.addAchievementToUser(id, Achievement.get("view_main"));
	}

	public static void firstLogin(Long userId, int loginCount) {
		if(loginCount==0){
			Achievement.addAchievementToUser(userId, Achievement.get("first_login"));
		}
	}
}
