package com.lambdaschool.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    //Environmental variables
    static final String CLIENT_ID = System.getenv("OAUTHCLIENTID");
    static final String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");

    // This is another way to login without having to create a username and password
    // It's another way to get authorized
    static final String GRANT_TYPE_PASSWORD = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";

    //Add scopes
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String SCOPE_TRUST = "trust";

    //By making it -1, it says the token will be valid until the session ends, so essentially no time limit
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
