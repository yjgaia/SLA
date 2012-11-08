package sla.ckfinder;

import javax.servlet.http.HttpServletRequest;

import com.ckfinder.connector.configuration.DefaultPathBuilder;

public class PathBuilder extends DefaultPathBuilder {

	// 개발
	//private final static String BASE_DIR = "/Users/hanul/Documents/workspace-sts-2.8.1.RELEASE/SLA-userfiles/WebContent";
	private final static String BASE_DIR = "D:/workspace/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/SLA-userfiles";
	private final static String BASE_URL = "http://test.com:8080/SLA-userfiles/";
	
	// 운영
	//private final static String BASE_DIR = "/root/apache-tomcat-7.0.32/webapps/run/SLA-userfiles";
	//private final static String BASE_URL = "http://yog.io/SLA-userfiles/";

	@Override
	public String getBaseDir(HttpServletRequest request) {
		return BASE_DIR + "/" + request.getSession().getAttribute("NOW_PAGE_ID");
	}

	@Override
	public String getBaseUrl(HttpServletRequest request) {
		return BASE_URL + request.getSession().getAttribute("NOW_PAGE_ID") + "/";
	}
}
