package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * The CRUD repository connecting Product to the rest of the application
 */
public interface ProductRepository extends CrudRepository<Product, Long>
{
}
