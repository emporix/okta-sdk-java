package com.okta.sdk.impl.oauth2;

import com.okta.commons.lang.Assert;
import com.okta.sdk.authc.credentials.ClientCredentials;


public class OAuth2HeaderTokenClientCredentials implements ClientCredentials<OAuth2AccessToken> {

    public static final ThreadLocal<OAuth2AccessToken> ACCESS_TOKEN = new ThreadLocal<>();

    @Override
    public OAuth2AccessToken getCredentials() {
        return ACCESS_TOKEN.get();
    }

    public void setAccessToken(OAuth2AccessToken accessToken) {
        Assert.notNull(accessToken, "accessToken may not be null");
        ACCESS_TOKEN.set(accessToken);
    }

    public void clearAccessToken() {
        ACCESS_TOKEN.remove();
    }

    @Override
    public String toString() {
        // never ever print the secret
        return "<OAuth2HeaderTokenClientCredentials>";
    }
}
