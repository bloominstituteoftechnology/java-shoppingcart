package com.lambdaschool.shoppingcart.services;


import com.lambdaschool.shoppingcart.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findRoleById(long id);

    Role save(Role role);
    Role findByName(String name);
    Role update(long id, Role role);
    public void deleteAll();
}
