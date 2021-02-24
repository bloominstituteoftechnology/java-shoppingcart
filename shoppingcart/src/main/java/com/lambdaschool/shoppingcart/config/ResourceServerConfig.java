package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
  private static String RESOURCE_ID = "resource_id";
  @Override
  public void configure (ResourceServerSecurityConfigurer resources)
  {
    resources.resourceId(RESOURCE_ID)
        .stateless(false);
  }
  @Override
  public void configure (HttpSecurity http) throws Exception
  {
    // who has access to what
    http.authorizeRequests()
        .antMatchers("/",
            "/h2-console/**",
            "/swagger-resources/**",
            "/swagger-resource/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**",
            "/createnewuser")
        .permitAll()
        .antMatchers("/users/**")
        .hasAnyRole("ADMIN")
        .antMatchers("/roles/**")
        .hasAnyRole("ADMIN")
        .antMatchers("/logout")
        .authenticated()
        .antMatchers("/carts/**")
        .authenticated()
        .antMatchers("/products/**")
        .hasAnyRole("ADMIN")
        .anyRequest().denyAll()
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new OAuth2AccessDeniedHandler());
    http.csrf().disable();
    http.headers().frameOptions().disable(); // required by H2
    http.logout().disable();
  }
}