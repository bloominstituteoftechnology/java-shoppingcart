package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * The CRUD repository connecting Product to the rest of the application
 */
public interface ProductRepository
        extends CrudRepository<Product, Long>
{
    @Transactional
    @Modifying
    @Query(value = "UPDATE products SET name = :name, price = :price, description = :description, comments = :comments, last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP where productid = :productid", nativeQuery = true)
    void updateProductInformation(String uname,
                                  long productid,
                                  String name,
                                  double price,
                                  String description,
                                  String comments);
}
