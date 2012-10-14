package sla.social;

import javax.inject.Inject;

import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("func/signin")
public class SocialSignInController extends ProviderSignInController {

	@Inject
	public SocialSignInController(
			ConnectionFactoryLocator connectionFactoryLocator,
			UsersConnectionRepository usersConnectionRepository,
			SignInAdapter signInAdapter) {
		super(connectionFactoryLocator, usersConnectionRepository, signInAdapter);
		
		setSignInUrl("/func/signin");
		setSignUpUrl("/func/signup");
	}

}
