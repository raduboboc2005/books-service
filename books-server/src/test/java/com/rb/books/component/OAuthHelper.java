package com.rb.books.component;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

@Component
@AllArgsConstructor
public class OAuthHelper {

    private ClientDetailsService clientDetailsService;
    private UserDetailsService userDetailsService;
    private AuthorizationServerTokenServices tokenService;

    public RequestPostProcessor bearerToken(final String clientId, final String username) {
        return mockRequest -> {
            OAuth2Authentication auth = oAuth2Authentication(clientId, username);
            OAuth2AccessToken token = tokenService.createAccessToken(auth);
            mockRequest.addHeader("Authorization", "Bearer " + token.getValue());
            return mockRequest;
        };
    }

    public OAuth2Authentication oAuth2Authentication(final String clientId, final String username) {

        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        Collection<GrantedAuthority> authorities = clientDetails.getAuthorities();

        Set<String> scopes = clientDetails.getScope();
        Set<String> resourceIds = clientDetails.getResourceIds();

        Map<String, String> requestParams = Collections.emptyMap();
        boolean approved = true;
        String redirectUrl = null;
        Set<String> responseTypes = Collections.emptySet();
        Map<String, Serializable> extensionProperties = Collections.emptyMap();

        OAuth2Request oAuth2Request = new OAuth2Request(requestParams,
                clientId, authorities, approved, scopes, resourceIds, redirectUrl, responseTypes, extensionProperties);

        UserDetails user = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
        OAuth2Authentication auth = new OAuth2Authentication(oAuth2Request, authenticationToken);
        return auth;
    }
}
