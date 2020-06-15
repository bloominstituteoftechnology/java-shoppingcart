package com.lambdaschool.shoppingcart.repositories;

import com.lambdaschool.shoppingcart.models.Cart;
import com.lambdaschool.shoppingcart.views.JustTheCount;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * The CRUD repository connecting Cart to the rest of the application
 */
public interface CartRepository
        extends CrudRepository<Cart, Long>
{
    List<Cart> findAllByUser_Userid(long id);

    @Query(value = "SELECT COUNT(*) as count FROM cartitems WHERE cartid = :cartid AND productid = :productid", nativeQuery = true)
    JustTheCount checkCartItems(long cartid,
                                long productid);

    @Transactional
    @Modifying
    @Query(value = "UPDATE cartitems SET quantity = (quantity + :adjustquantity), last_modified_by = :uname, last_modified_date = CURRENT_TIMESTAMP  WHERE cartid = :cartid AND productid = :productid", nativeQuery = true)
    void updateCartItemsQuantity(String uname,
                                 long cartid,
                                 long productid,
                                 long adjustquantity);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO cartitems (cartid, productid, quantity, created_by, created_date, last_modified_by, last_modified_date) VALUES (:cartid, :productid, 1, :uname, CURRENT_TIMESTAMP , :uname, CURRENT_TIMESTAMP)", nativeQuery = true)
    void addCartItems(String uname,
                      long cartid,
                      long productid);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM cartitems WHERE quantity <= 0", nativeQuery = true)
    void removeCartItemsQuantityZero();

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM carts WHERE cartid NOT IN (SELECT cartid FROM cartitems)", nativeQuery = true)
    void removeCartWithNoProducts();
}
