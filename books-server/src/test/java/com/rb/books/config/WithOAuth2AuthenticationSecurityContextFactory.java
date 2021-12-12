package com.rb.books.config;

import com.rb.books.component.OAuthHelper;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;

@AllArgsConstructor
public class WithOAuth2AuthenticationSecurityContextFactory {
	public OAuthHelper helper;

	public SecurityContext createSecurityContext() {
		OAuth2Authentication oAuth2Authentication = helper.oAuth2Authentication("client_app", "test_user");
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(oAuth2Authentication);
		return context;
	}
}
