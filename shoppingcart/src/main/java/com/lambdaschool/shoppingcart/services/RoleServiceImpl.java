package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.Role;
import com.lambdaschool.shoppingcart.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "roleService")
public class RoleServiceImpl implements RoleService{

    @Autowired
    RoleRepository rolerepos;

    @Override
    public Role findRoleById(long roleid) {
        Role role = rolerepos.findById(roleid)
                .orElseThrow(() -> new ResourceNotFoundException("Role" + roleid + " not found!"));
        return role;
    }
}
