package com.lambdaschool.shoppingcart.services;


import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "securityUserDetails")
public class SecurityUserServiceImpl implements UserDetailsService {

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByUsername(username.toLowerCase());
    if (user == null) {
      throw new ResourceNotFoundException("Invalid username or password");
    }
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), user.getPassword(), user.getAuthority());
  }
}
