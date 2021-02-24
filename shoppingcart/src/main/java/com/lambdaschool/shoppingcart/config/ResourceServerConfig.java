package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources)
    {
        resources.resourceId(RESOURCE_ID)
                .stateless(false);
    }
//
//    For the routes /carts/**
// All authenticated users can access /carts/***
// Remove the user/{userid} path variable from all the routes
// Use the authenticated as the user to work with

    @Override
    public void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/roles/**", "/products/**")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/users/user")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "user/users/{id}")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/users/user/{id}")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/users/user/name/{userName}",
                        "/users/user/name/like/{userName}", "users/user","/users/user/{userid}")
                .hasAnyRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/users/user/{id}")
                .hasAnyRole("ADMIN")
                .antMatchers("/carts/**")
                .authenticated()
                .anyRequest().denyAll()
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();

        http.headers().frameOptions().disable();

        http.logout().disable();


    }
}
