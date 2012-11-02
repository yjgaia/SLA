package sla.ckfinder;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import sla.util.AuthUtil;
import sla.util.IpUtil;

import com.ckfinder.connector.ServletContextFactory;
import com.ckfinder.connector.configuration.DefaultPathBuilder;

public class PathBuilder extends DefaultPathBuilder {
	
	@Override
	public String getBaseDir(HttpServletRequest request) {
		String newBaseUrl = getBaseUrl(request);
		newBaseUrl = newBaseUrl.substring(request.getContextPath().length());
		
		try {
			return ServletContextFactory.getServletContext().getRealPath(newBaseUrl);
		} catch (Exception e) {
			return newBaseUrl;
		}
	}
	
	private static Map<String, String> savedUsername = new HashMap<String, String>();

	@Override
	public String getBaseUrl(HttpServletRequest request) {
		String username = AuthUtil.getUsername();
		if (username == null) {
			username = savedUsername.get(IpUtil.getIp(request));
		} else {
			savedUsername.put(IpUtil.getIp(request), username);
		}
		return super.getBaseUrl(request) + "/" + username + "/";
	}
}
