package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.Product;

import java.util.List;

/**
 * The Service that works with Product Model.
 */
public interface ProductService
{
    /**
     * Returns a list of all the Products
     *
     * @return List of Products. If no Products, empty list.
     */
    List<Product> findAll();

    /**
     * Returns the Product with the given primary key.
     *
     * @param id The primary key (long) of the Product you seek.
     * @return The given Product or throws an exception if not found.
     */
    Product findProductById(long id);

    /**
     * Deletes the Product record from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the Product you seek.
     */
    void delete(long id);

    /**
     * Given a complete Product object, saves that Product object in the database.
     * If a primary key is provided, the record is completely replaced
     * If no primary key is provided, one is automatically generated and the record is added to the database.
     *
     * @param product the Product object to be saved
     * @return the saved Product object including any automatically generated fields
     */
    Product save(Product product);

    /**
     * Updates the given product
     *
     * @param id      The primary key (long) of the product you wish to update
     * @param product The product object containing the new information - only the product can be updated here, no carts!
     * @return The complete product with the field changes
     */
    Product update(
            long id,
            Product product);
}
