package sla.social;


import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.social.facebook.api.Comment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.FacebookProfile;
import org.springframework.social.facebook.api.Post;
import org.springframework.social.facebook.api.Reference;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.TwitterProfile;
import org.springframework.web.context.request.NativeWebRequest;

import sla.model.ShortUrl;
import sla.model.UserInfo;
import sla.service.AchievementService;
import sla.util.AuthUtil;

public final class SocialSignInAdapter implements SignInAdapter {

	private final RequestCache requestCache;

	@Inject
	public SocialSignInAdapter(RequestCache requestCache) {
		this.requestCache = requestCache;
	}
	
	@Override
	public String signIn(String userId, Connection<?> connection, NativeWebRequest request) {


		
		// 유저 정보 로드
		UserInfo userInfo = UserInfo.findUserInfo(Long.valueOf(userId));
		userInfo.setLastLoginDate(new Date());
		userInfo.increaseLoginCount();
		
		//loginCount관련 업적 추가
		int loginCount=userInfo.getLoginCount();
		AchievementService.loginCountAchievement(userInfo.getId(),loginCount);
		
		if (connection.getApi() instanceof Facebook) { //변경 되는 페이스북 정보이므로 매번 체크해서 업데이트 해줌
			Facebook facebook = (Facebook) connection.getApi();
			FacebookProfile fp = facebook.userOperations().getUserProfile();
			userInfo.setSocialName(fp.getName());
			userInfo.setSocialBirthday(fp.getBirthday());
			userInfo.setSocialEmail(fp.getEmail());
			userInfo.setSocialGender(fp.getGender());
			List<String> friendList=facebook.friendOperations().getFriendIds();
			userInfo.setSocialFriendCount(friendList.size());
			
			List<ShortUrl> shortUrlList=ShortUrl.findShortUrlsByUserId(userInfo.getId());
			int size=shortUrlList.size();
			for(int i=0;i<size;i++){
				ShortUrl su=shortUrlList.get(i);
				
				if(su.getEntityId()!=null){
					try{
					ObjectMapper objMapper=new ObjectMapper();
					Post post=facebook.feedOperations().getPost(su.getEntityId());
					if(post!=null){
						List<Comment> commentList=post.getComments();
						if(commentList!=null){
							su.setComments(objMapper.writeValueAsString(commentList));
						}
						List<Reference> likes=facebook.likeOperations().getLikes(su.getEntityId());
						if(likes!=null){
							su.setLikes(objMapper.writeValueAsString(likes));
							su.setLikeCount(post.getLikeCount());
						}
						
					}
					}catch (Exception e){
						System.out.println("exception:"+e.toString());
					}
					su.merge();
				}
			}
		}
		if (connection.getApi() instanceof Twitter) { // 변경 되는 트위터 정보이므로 매번 체크해서 업데이트 해줌
			Twitter twitter = (Twitter) connection.getApi();
			TwitterProfile tp = twitter.userOperations().getUserProfile();
			System.out.println("Name: " + tp.getName());
			userInfo.setSocialName(tp.getName());
			System.out.println("friendList("+tp.getFollowersCount()+")");
			userInfo.setSocialFriendCount(tp.getFollowersCount());
		}
		userInfo.merge();
		
		AuthUtil.auth(userInfo);
		return extractOriginalUrl(request);
	}

	private String extractOriginalUrl(NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse nativeRes = request.getNativeResponse(HttpServletResponse.class);
		SavedRequest saved = requestCache.getRequest(nativeReq, nativeRes);
		if (saved == null) {
			return null;
		}
		requestCache.removeRequest(nativeReq, nativeRes);
		removeAutheticationAttributes(nativeReq.getSession(false));
		
		return saved.getRedirectUrl();
	}
		 
	private void removeAutheticationAttributes(HttpSession session) {
		if (session == null) {
			return;
		}
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}

}