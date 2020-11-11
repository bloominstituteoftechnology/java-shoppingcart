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
                .stateless(false);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",      //Note: "ant" stands for "another neat tool". Ant matchers let us specify our route
                        "/h2-console/**",
                        //There are a bunch  more things to add here for swagger
                        "/swagger-resources/**",
                        "/swagger-resource/**",
                        "/swagger-ui.html",
                        "/v2/api-docs",
                        "/webjars/**",
                        "/createnewuser"
                )
                .permitAll()
                .antMatchers("/roles/**", "/products/**")
                .hasAnyRole("ADMIN")  //Only admin can see
                .antMatchers("/logout")
                .authenticated()
                //If a route has already been declared, antMatchers will ignore it if you try to use it again
                .and()
                .exceptionHandling() //Dealing with exceptions to the above
                .accessDeniedHandler(new OAuth2AccessDeniedHandler());

//Disable cross site request authority, headers frame options, and logout //CHECK GP

        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.logout().disable();

    }
}
