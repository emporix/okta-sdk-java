package com.okta.sdk.impl.oauth2

import org.testng.annotations.Test

import static org.testng.Assert.assertEquals

class OAuth2AccessTokenTest {
    def token = 'eyJraWQiOiJROGVvZ0w1S1MyRVlNenlvMzdLMGNuWDI0UTVvMVNxUlZQeFVIVlZmNlEwIiwiYWxnIjoiUlMyNTYifQ.eyJ2ZXIiOjEsImp0aSI6IkFULkp6VXdtLUtxN1I3LVJVblhQSkRBSDF6NTdXMmZIalZjT2VRTHE1M3ZyUjgiLCJpc3MiOiJodHRwczovL2VtcG9yaXgtZGV2Lm9rdGFwcmV2aWV3LmNvbSIsImF1ZCI6Imh0dHBzOi8vZW1wb3JpeC1kZXYub2t0YXByZXZpZXcuY29tIiwic3ViIjoia3J6eXN6dG9mLm11Y2hhQGNvYnJpY2suY29tIiwiaWF0IjoxNjMxMDgzNjEyLCJleHAiOjE2MzEwODcyMTIsImNpZCI6IjBvYTF2MG9lcHhrNjdqN3dSMHg3IiwidWlkIjoiMDB1MXJnMGd4NEo3Vm9ycXAweDciLCJzY3AiOlsib2t0YS51c2Vycy5yZWFkIl19.ej7Gsck2jaX4Gf2qbpqmaa2fQitkST_hVqbPPk7UP1AaKkqcfKC1LLSG0GAprKhi56aS2xECBw5gK6uZkaUsJOowC1or83zNLTJPgop0rPmRfdozPkGw_cJ6D85vg78qQoy67DEklApoSKxtjvmSwgyUP4CfFPN0tO_D8lhC0qiIx48iTkGCyZkqQKRxI6HSOdlqH1RrQ1s_DyrsNe-OA_F8qa8PArO6aDCMA1qkPjTGjm77UGvLlvpPP2YNWg7ceOtQxvWU-640XaBcIvcX1PyI0OGGF1mErco5YjJUZZCOaCoZZqJixpGVovsV6LRV3XsXrq6l_oe9I8RwHzetYg'

    @Test
    void shouldConstructValidObjectWhenTokenIsProvided() {
        def accessToken = 'Bearer ' + token
        def auth2AccessToken = OAuth2AccessToken.of(accessToken)

        assertEquals(auth2AccessToken.getTokenType(), 'Bearer ')
        assertEquals(auth2AccessToken.getAccessToken(), token)
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenTokenTypeIsInvalid() {
        def accessToken = 'Bearer' + token

        OAuth2AccessToken.of(accessToken)
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenTokenTypeIsMissing() {
        OAuth2AccessToken.of(token)
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenTokenIsNull() {
        OAuth2AccessToken.of(null)
    }
}
