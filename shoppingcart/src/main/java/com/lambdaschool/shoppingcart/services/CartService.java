package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.Cart;
import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.models.User;

import java.util.List;

/**
 * The Service that works with Cart Model.
 */

public interface CartService
{
    // find all carts for a user
    // find cart by id
    // user adds Cart without cart create new cart
    // user adds Cart with cart
    // user removed Cart from cart. If cart is empty, delete cart

    /**
     * Returns a list of all the Carts for the given user id
     *
     * @param userid userid that you seek
     * @return List of Carts. If no Carts, empty list.
     */
    List<Cart> findAllByUserId(Long userid);

    /**
     * Returns the Cart with the given primary key.
     *
     * @param id The primary key (long) of the Cart you seek.
     * @return The given Cart or throws an exception if not found.
     */
    Cart findCartById(long id);

    /**
     * Creates a new cart for this user populated with this product
     *
     * @param user    the user to be saved
     * @param product the product to be saved
     * @return the saved Cart object including any automatically generated fields
     */
    Cart save(User user,
              Product product);

    /**
     * Creates a new product for this cart
     *
     * @param cart    the cart to gain the product
     * @param product the product to be added
     * @return the saved Cart object including any automatically generated fields
     */
    Cart save(Cart cart,
              Product product);

    /**
     * Removes product from this cart
     *
     * @param cart    the cart to lose the product
     * @param product the product to be removed
     */
    void delete(Cart cart,
                Product product);
}
