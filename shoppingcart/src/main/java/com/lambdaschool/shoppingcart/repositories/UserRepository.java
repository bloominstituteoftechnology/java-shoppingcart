package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository
        extends CrudRepository<User, Long>
{
    User findByUsername(String username);

    /**
     * Find all users whose name contains a given substring ignoring case
     *
     * @param name the substring of the names (String) you seek
     * @return List of users whose name contain the given substring ignoring case
     */
    List<User> findByUsernameContainingIgnoreCase(String name);
}
