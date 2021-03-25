package com.lambdaschool.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {





    private String CLIENT_ID = System.getenv("OAUTHCLIENTID");

    private String CLIENT_SECRET = System.getenv("OAUTHCLIENTSECRET");


    private String GRANT_TYPE_PASSWORD = "LambdaLlama";
    private String AUTHORIZATION_CODE = "authorization_code";
    private String SCOPE_READ = "read";
    private String SCOPE_WRITE = "write";
    private String SCOPE_TRUST = "trust";

    private final int ACCESS_TOKEN_VALIDILITY_SECONDS = -1;

    @Autowired
  private TokenStore tokenStore;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer) throws Exception{

        configure.inMemory()
                .withClient(CLIENT_ID)
                .secret(encoder.encode(CLIENT_SECRET))
                .authroizedGrantTypes(GRANT_TYPE_PASSWORD,AUTHORIZATION_CODE)
                .scopes(SCOPE_READ,SCOPE_TRUST, SCOPE_WRITE)
                .accessTodenValiditySeconds(ACCESS_TOKEN_VALIDILITY_SECONDS)
    }
    @Override
    public void configure (AuthorizationServerEndpointsConfigurer endpoints)
    {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager);
                endpoints.pathMapping("/oauth/token", "/login");
    }

}
