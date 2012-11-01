package sla.social;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.view.RedirectView;

@RequestMapping("func/signin")
public class SocialSignInController extends ProviderSignInController {
	
	private static final String REDIRECT_URI = "SPRING_SECURITY_REDIRECT_URI";
	private static final String REDIRECT_URI_PRAMETER = "redirect_uri";

	@Inject
	public SocialSignInController(
			ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository usersConnectionRepository,
			SignInAdapter signInAdapter) {
		super(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
		
		setSignInUrl("/func/signin");
		setSignUpUrl("/func/signup");
	}
	
	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.POST)
	public RedirectView signIn(@PathVariable String providerId, NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		nativeReq.getSession(false).setAttribute(REDIRECT_URI, request.getParameter(REDIRECT_URI_PRAMETER));
		return super.signIn(providerId, request);
	}
	
	private RedirectView r(RedirectView ret, NativeWebRequest request) {
		HttpServletRequest nativeReq = request.getNativeRequest(HttpServletRequest.class);
		HttpSession nativeSession = nativeReq.getSession(false);
		String redirectUri = (String) nativeSession.getAttribute(REDIRECT_URI);
		if (redirectUri != null) {
			ret.setUrl(redirectUri);
			nativeSession.removeAttribute(REDIRECT_URI);
		}
		return ret;
	}
	
	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="oauth_token")
	public RedirectView oauth1Callback(@PathVariable String providerId, NativeWebRequest request) {
		return r(super.oauth1Callback(providerId, request), request);
	}
	
	@Override
	@RequestMapping(value="/{providerId}", method=RequestMethod.GET, params="code")
	public RedirectView oauth2Callback(@PathVariable String providerId, @RequestParam("code") String code, NativeWebRequest request) {
		return r(super.oauth2Callback(providerId, code, request), request);
	}

}
