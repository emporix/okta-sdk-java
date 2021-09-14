package com.okta.sdk.impl.resource;

import com.okta.sdk.client.Client;
import com.okta.sdk.resource.Introspect;
import com.okta.sdk.resource.introspect.IntrospectTokenBuilder;

public class DefaultIntrospectTokenBuilder implements IntrospectTokenBuilder {

    private String tokenTypeHint;
    private String token;
    private String clientId;

    @Override
    public IntrospectTokenBuilder setToken(final String token) {
        this.token = token;
        return this;
    }

    @Override
    public IntrospectTokenBuilder setTokenHint(final String tokenTypeHint) {
        this.tokenTypeHint = tokenTypeHint;
        return this;
    }

    @Override
    public IntrospectTokenBuilder setClientId(final String clientId) {
        this.clientId = clientId;
        return this;
    }

    @Override
    public Introspect buildAndCreate(Client client) {
        return client.introspectToken(clientId, token, tokenTypeHint);
    }
}