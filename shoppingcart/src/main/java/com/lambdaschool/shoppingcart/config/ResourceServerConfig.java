package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
  private static final String RESOURCE_ID = "resource_id";

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources.resourceId(RESOURCE_ID)
             .stateless(false);//Does not need username for testing
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {//which roles has access to which endpoints
    http.authorizeRequests()
        .antMatchers("/", "/h2-console/**",
                     "/swagger-resources/**",
                     "/swagger-resource/**",
                     "/swagger-ui.html",
                     "/v2/api-docs",
                     "/webjars/**",
                     "/createnewuser",
                     "/signup",
                     "/login")
        .permitAll()
        .antMatchers("/roles/**")
        .hasAnyRole("ADMIN", "ANALYST")
        .antMatchers("/users/**", "/logout")
        .authenticated()
          .antMatchers("/carts/**")
          .hasAnyRole("ADMIN", "ANALYST")
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());

    http.csrf().disable();
    http.headers().frameOptions().disable(); //Allows h2 console to work
    http.logout().disable(); //We will write a custom logout
  }
}
