package com.lambdaschool.shoppingcart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    @Bean
    AuthenticationManager authenticationManager() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Autowired
    private UserDetailsService securityUserService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception
        throws
            exception
    {
        auth.UserDetailsService(securityUserService)
    }           .passwordEncoder(encoder());

    @Bean
    public TokenStore tokenStore()
    {
        return new inMemoryTokenStore();


    }
  @Bean
  { public PasswordEncoder encoder()
      return new BcryptPasswordEncoder();

}



