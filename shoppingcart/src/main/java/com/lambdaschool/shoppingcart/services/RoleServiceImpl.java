package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service(value = "roleService")
public class RoleServiceImpl {

    @Autowired
    RoleRepository rolerepos;
}