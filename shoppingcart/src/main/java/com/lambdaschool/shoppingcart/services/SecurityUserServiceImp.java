package com.lambdaschool.shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service(value = "securityUserService")
public class SecurityUserServiceImp implements UserDetailsService {

    @Autowired
    private UserRepository userRepos;
    @Override
    public UserDetails loadUserByUsername(String s) throws ResourceNotFoundException {

            user user = userRepos.findByUsername(s.toLowerCase();
        if (user == null){

            throw new ResourceNotFoundException("invalid username or password")
        }
            return org.springframework.security.core.userdetails.User(user.getUsername()
                    user.getPassword(), user.getAuthority());
        }
}
