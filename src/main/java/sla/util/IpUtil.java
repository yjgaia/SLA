package sla.util;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

}
