package com.lambdaschool.shoppingcart.repository;

import com.lambdaschool.shoppingcart.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long>
{
}
