package com.lambdaschool.shoppingcart.config;

import com.sun.xml.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

@Autowired
private TokenStore tokenStore;
@Autowired
private AuthenticationManager authenticationManager;

@Autowired

private PasswordEncoder encoder;


    private String CLIENT_ID = System.getenv("OAUTHCLIENTID");

    private String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");


    private String GRANT_TYPE_PASSWORD = "password";
    private String AUTHORIZATION_CODE = "authorization_code";
    private String SCOPE_READ = "read";
    private String SCOPE_WRITE = "write";
    private String SCOPE_TRUST = "trust";

    private final int ACCESS_TOKEN_VALIDILITY_SECONDS = -1;


    @Override
    public void configure(ClientDetailsServiceConfigurer configurer) throws Exception{
               configurer.inMemory().secret(encoder.encode(CLIENT_SECRET))
                .authroizedGrantTypes(GRANT_TYPE_PASSWORD,AUTHORIZATION_CODE)
                .scopes(SCOPE_READ,SCOPE_TRUST, SCOPE_WRITE)
                .accessTodenValiditySeconds(ACCESS_TOKEN_VALIDILITY_SECONDS);
    }
    @Override
    public void configure (AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
                endpoints.pathMapping("/oauth/token", "/login");
    }

}
