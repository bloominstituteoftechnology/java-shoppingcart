package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.Cart;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting Cart to the rest of the application
 */
public interface CartRepository extends CrudRepository<Cart, Long>
{
}
