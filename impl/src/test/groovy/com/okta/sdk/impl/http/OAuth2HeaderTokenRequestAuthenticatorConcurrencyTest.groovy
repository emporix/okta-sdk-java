package com.okta.sdk.impl.http

import com.okta.commons.http.HttpHeaders
import com.okta.commons.http.Request
import com.okta.sdk.impl.http.authc.OAuth2HeaderTokenRequestAuthenticator
import com.okta.sdk.impl.oauth2.OAuth2AccessToken
import com.okta.sdk.impl.oauth2.OAuth2HeaderTokenClientCredentials
import org.testng.annotations.AfterTest
import org.testng.annotations.BeforeTest
import org.testng.annotations.Test

import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.Mockito.*
import static org.testng.Assert.assertEquals

class OAuth2HeaderTokenRequestAuthenticatorConcurrencyTest {
    OAuth2HeaderTokenClientCredentials clientCredentials = new OAuth2HeaderTokenClientCredentials()
    OAuth2HeaderTokenRequestAuthenticator authenticator

    def request = mock(Request)
    def httpHeaders = mock(HttpHeaders)

    @BeforeTest
    void initialize() {
        authenticator = new OAuth2HeaderTokenRequestAuthenticator(clientCredentials)
        when(request.getHeaders()).thenReturn(httpHeaders)
    }

    @Test(threadPoolSize = 5, invocationCount = 50)
    void testAuthenticateRequestWithExpiredInitialToken() {
        def accessToken = OAuth2AccessToken.of('Bearer ' + UUID.randomUUID().toString())
        clientCredentials.setAccessToken(accessToken)

        authenticator.authenticate(request)
        Thread.sleep((long) (Math.random() * 1000)) /* sleep random time (max 1000 ms) */

        assertEquals(clientCredentials.getCredentials().getAccessToken(), accessToken.getAccessToken())
    }

    @Test(threadPoolSize = 5, invocationCount = 50)
    void testAuthenticateRequestWithExpiredInitialToken1() {
        def accessToken = OAuth2AccessToken.of('Bearer ' + UUID.randomUUID().toString())
        clientCredentials.setAccessToken(accessToken)

        authenticator.authenticate(request)
        Thread.sleep((long) (Math.random() * 1000)) /* sleep random time (max 1000 ms) */
        clientCredentials.clearAccessToken()

        assertEquals(clientCredentials.getCredentials(), null)
    }

    @AfterTest
    void verifyMocks() {
        verify(request.getHeaders(), times(100)).set(anyString(), anyString())
    }
}