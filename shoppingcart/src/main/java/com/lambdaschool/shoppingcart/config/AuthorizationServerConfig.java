package com.lambdaschool.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter
{
    static final String CLIENT_ID = "lambda-id"; // clients username
    static final String CLIENT_SECRET = "lambda-secret"; // password
    static final String GRANT_TYPE_PASSWORD = "password"; // when a user (authenticated) or a client (authorized) wants to get access, send a password
    static final String AUTHORIZATION_CODE = "authorization_code";
    // scope is another way to further define access - info in TK if I want to learn
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String SCOPE_TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = -1; // how long do we want valid? -1 = As long as application is running or log out --- this is in seconds

    // auth token tells us which user is which -- we need to tell spring how to store, create, etc:
    @Autowired
    private TokenStore tokenStore; // will tie in authentication we will be doing but need to tell it where the tokens are stored

    @Autowired
    private AuthenticationManager authenticationManager; // tell spring we have one

    @Autowired
    private PasswordEncoder encoder; // encrypt passwords -- can't easily go back to plain text
    // will be using encryption scheme

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception
    {
        // Actually does configuration for us:
        clients.inMemory() // everything we do, we save in memory bc it's fast and secure, and can quickly kick off everyone in the system by shutting it off -- if stored on disk, people will still have access when I turn it back on
            .withClient(CLIENT_ID)
            .secret(encoder.encode(CLIENT_SECRET))
            .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE)
            .scopes(SCOPE_READ, SCOPE_WRITE, SCOPE_TRUST)
            .accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception
    {
        // tell where our token store is (token store above) and where auth manager is
        endpoints.tokenStore(tokenStore)
            .authenticationManager(authenticationManager);
        endpoints.pathMapping("/oauth/token", "/login"); // ( default path, instead - go to this)
    }
}
