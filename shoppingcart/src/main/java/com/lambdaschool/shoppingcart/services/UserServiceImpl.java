package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceFoundException;
import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.Role;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.UserRoles;
import com.lambdaschool.shoppingcart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements UserService Interface
 */
@Transactional
@Service(value = "userService")
public class UserServiceImpl
    implements UserService
{
    /**
     * Connects this service to the User table.
     */
    @Autowired
    private UserRepository userrepos;

    /**
     * Connects this service to the Role table
     */
    @Autowired
    private RoleService roleService;

    public User findUserById(long id) throws
                                      ResourceNotFoundException
    {
        return userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
    }

    @Override
    public List<User> findByNameContaining(String username)
    {
        return userrepos.findByUsernameContainingIgnoreCase(username.toLowerCase());
    }

    @Override
    public List<User> findAll()
    {
        List<User> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        userrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        userrepos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User id " + id + " not found!"));
        userrepos.deleteById(id);
    }

    @Override
    public User findByName(String name)
    {
        User uu = userrepos.findByUsername(name.toLowerCase());
        if (uu == null)
        {
            throw new ResourceNotFoundException("User name " + name + " not found!");
        }
        return uu;
    }

    @Transactional
    @Override
    public User save(User user)
    {
        if (user.getCarts()
            .size() > 0)
        {
            throw new ResourceFoundException("Carts are not created via Users");
        }

        User newUser = new User();

        if (user.getUserid() != 0)
        {
            newUser = userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found!"));
        }

        newUser.setUsername(user.getUsername()
            .toLowerCase());
        newUser.setPasswordNoEncrypt(user.getPassword());
        newUser.setPrimaryemail(user.getPrimaryemail()
            .toLowerCase());

        newUser.getRoles()
            .clear();
        for (UserRoles ur : user.getRoles())
        {
            Role addRole = roleService.findRoleById(ur.getRole()
                .getRoleid());
            newUser.getRoles()
                .add(new UserRoles(newUser,
                    addRole));
        }

        return userrepos.save(newUser);
    }

    @Transactional
    @Override
    public User update(
        User user,
        long id)
    {
        if (user.getCarts()
            .size() > 0)
        {
            throw new ResourceFoundException("Carts are not updated via Users");
        }

        User currentUser = findUserById(id);

        if (user.getUsername() != null)
        {
            currentUser.setUsername(user.getUsername()
                .toLowerCase());
        }

        if (user.getPassword() != null)
        {
            currentUser.setPasswordNoEncrypt(user.getPassword());
        }

        if (user.getPrimaryemail() != null)
        {
            currentUser.setPrimaryemail(user.getPrimaryemail()
                .toLowerCase());
        }

        if (user.getComments() != null)
        {
            currentUser.setComments(user.getComments());
        }

        if (user.getRoles()
            .size() > 0)
        {
            currentUser.getRoles()
                .clear();
            for (UserRoles ur : user.getRoles())
            {
                Role addRole = roleService.findRoleById(ur.getRole()
                    .getRoleid());

                currentUser.getRoles()
                    .add(new UserRoles(currentUser,
                        addRole));
            }
        }

        return userrepos.save(currentUser);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void deleteAll()
    {
        userrepos.deleteAll();
    }
}
