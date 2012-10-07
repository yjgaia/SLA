package kr.co.siterush.util;

import kr.co.siterush.model.UserInfo;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author 심영재
 */
public class AuthUtil {

	private AuthUtil() {
		// 임의 생성 금지
	}
	
	public static void auth(UserInfo userInfo) {
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities()));
	}

	public static UserInfo getUserInfo() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext == null) {
			return null;
		}
		Authentication authentication = securityContext.getAuthentication();
		if (authentication == null) {
			return null;
		}
		Object princiapl = authentication.getPrincipal();
		if (!(princiapl instanceof UserInfo)) {
			return null;
		}
		return (UserInfo) princiapl;
	}
	
	public static void setUserInfo(UserInfo userInfo) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if (securityContext == null) {
			return;
		}
		SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userInfo, null, userInfo.getAuthorities()));
	}
	
	public static Long getUserId() {
		UserInfo userInfo = getUserInfo();
		return userInfo == null ? null : userInfo.getId();
	}
	
	public static String getUsername() {
		UserInfo userInfo = getUserInfo();
		return userInfo == null ? null : userInfo.getUsername();
	}

	public static boolean isAnonymous() {
		return getUserInfo() == null;
	}

	public static boolean isAuthenticated() {
		return getUserInfo() != null;
	}

}
