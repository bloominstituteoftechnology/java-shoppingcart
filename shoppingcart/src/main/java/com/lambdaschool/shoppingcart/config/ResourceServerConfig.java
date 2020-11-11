package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Configuration;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
    private static final String RESOURCE_ID = "resource_id";

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resouceId(RESOURCE_ID)
            .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
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
            .antMatchers("/roles/**")
            .hasAnyRole("ADMIN")
            .antMatchers("/users/**", "/logout")
            .authenticated()
            .antMatchers("/useremails/**")
            .hasAnyRole("ADMIN", "DATA")
            .and()
            .exceptionHandling()
            .accessDeniedHandler(new OAuth2AccessDeniedHandler());

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();
    }
}

