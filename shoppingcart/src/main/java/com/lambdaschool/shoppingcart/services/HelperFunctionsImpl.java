package com.lambdaschool.shoppingcart.services;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service(value = "helpFunctions")
public class HelperFunctionsImpl
    implements HelperFunctions
{
    @Override
    public boolean isAuthorizedToMakeChange(String username)
    {
        // Check to see if the user whose information being requested is the current user
        // Check to see if the requesting user is an admin
        // if either is true, return true
        // otherwise stop the process and throw an exception
        Authentication authentication = SecurityContextHolder.getContext()
            .getAuthentication();
        if (username.equalsIgnoreCase(authentication.getName()
            .toLowerCase()) || authentication.getAuthorities()
            .contains(new SimpleGrantedAuthority("ADMIN")))
        {
            // this user can make this change
            return true;
        } else
        {
            // stop user is not authorized to make this change so stop the whole process and throw an exception
            throw new EntityNotFoundException(authentication.getName() + " not authorized to make change");
        }
    }
}
