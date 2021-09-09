package com.okta.sdk.impl.http.authc;

import com.okta.commons.http.Request;
import com.okta.commons.http.authc.RequestAuthenticationException;
import com.okta.commons.http.authc.RequestAuthenticator;
import com.okta.commons.lang.Assert;
import com.okta.sdk.authc.credentials.ClientCredentials;
import com.okta.sdk.impl.oauth2.OAuth2AccessToken;

/**
 * This implementation used by OAuth2 flow adds Bearer header with access token as the
 * value in all outgoing requests.
 */
public class OAuth2HeaderTokenRequestAuthenticator implements RequestAuthenticator {
    private final ClientCredentials<OAuth2AccessToken> clientCredentials;

    public OAuth2HeaderTokenRequestAuthenticator(ClientCredentials<OAuth2AccessToken> clientCredentials) {
        Assert.notNull(clientCredentials, "clientCredentials may not be null");
        this.clientCredentials = clientCredentials;
    }

    @Override
    public void authenticate(Request request) throws RequestAuthenticationException {
        OAuth2AccessToken oAuth2AccessToken = clientCredentials.getCredentials();

        request.getHeaders().set(AUTHORIZATION_HEADER, oAuth2AccessToken.getTokenType() + oAuth2AccessToken.getAccessToken());
    }
}
