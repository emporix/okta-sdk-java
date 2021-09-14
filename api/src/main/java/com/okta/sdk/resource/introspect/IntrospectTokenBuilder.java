package com.okta.sdk.resource.introspect;

import com.okta.commons.lang.Classes;
import com.okta.sdk.client.Client;
import com.okta.sdk.resource.Introspect;

public interface IntrospectTokenBuilder {

    static IntrospectTokenBuilder instance() {
        return Classes.newInstance("com.okta.sdk.impl.resource.DefaultIntrospectBuilder");
    }

    IntrospectTokenBuilder setToken(String token);

    IntrospectTokenBuilder setTokenHint(String tokenTypeHint);

    IntrospectTokenBuilder setClientId(String clientId);

    Introspect buildAndCreate(Client client);
}
