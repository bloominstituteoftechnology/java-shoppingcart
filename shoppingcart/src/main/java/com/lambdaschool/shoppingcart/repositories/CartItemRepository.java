package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.CartItem;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting CartItem to the rest of the application
 */
public interface CartItemRepository extends CrudRepository<CartItem, Long>
{
}
