package com.lambdaschool.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
    static final String CLIENT_ID = System.getenv( "OATHCLIENTID");
    static final String CLIENT_SECRET = System.getenv( "OATHCLIENTSECRET");

    static final String GRANT_TYPE_PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";

    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String SCOPE_TRUST = "trust";

    static final int ACCESS_TOKEN_VALIDITY_SECONDS = -1;

    @Autowired
    private TokenStore tokenStore;


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception {
        configurer.inMemory()
            .withClient(CLIENT_ID)
            .secret(passwordEncoder.encode(CLIENT_SECRET))
            .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE)
            .scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
            .authenticationManager(authenticationManager);
        endpoints.pathMapping("/oauth/token", "/login");
    }
}

