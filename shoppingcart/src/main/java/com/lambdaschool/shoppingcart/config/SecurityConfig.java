package com.lambdaschool.shoppingcart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
    privare UserDetailsService securityUserService;

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth)
        throws
            exception
    {
        auth.userDetailsService(securityUserService)
    }           .passwordEncoder(encoder());

    @Bean
    public TokenStore tokenStore()
    {
        return new inMemoryTokenStore();


    }
  @BeAn
    public PasswordEncoder encoder()
      return new BcryptPasswordEncoder();

}



