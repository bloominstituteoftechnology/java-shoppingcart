package com.lambdaschool.shoppingcart.controllers;

import com.lambdaschool.shoppingcart.models.Cart;
import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.services.CartService;
import com.lambdaschool.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/user", produces = {"application/json"})
    public ResponseEntity<?> listAllCarts(Authentication authentication)
    {
         User user = userService.findByUsername(authentication.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping(value = "/cart/{cartId}",
            produces = {"application/json"})
    public ResponseEntity<?> getCartById(
            @PathVariable
                    Long cartId)
    {
        Cart p = cartService.findCartById(cartId);
        return new ResponseEntity<>(p,
                                    HttpStatus.OK);
    }

    @PostMapping(value = "/create/user/product/{productid}")
    public ResponseEntity<?> addNewCart(Authentication authentication,
                                        @PathVariable long productid)
    {
        User dataUser = new User();
        dataUser = userService.findByUsername(authentication.getName());

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.save(dataUser, dataProduct);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value = "/update/cart/{cartid}/product/{productid}")
    public ResponseEntity<?> updateCart(@PathVariable long cartid,
                                        @PathVariable long productid,
                                        Authentication authentication)
    {

        User user = new User();
        user = userService.findByUsername(authentication.getName());

        Cart dataCart = new Cart ();
        dataCart.setCartid(cartid);

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.save(user, dataCart, dataProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/cart/{cartid}/product/{productid}")
    public ResponseEntity<?> deleteFromCart(@PathVariable long cartid,
                                            @PathVariable long productid,
                                            Authentication authentication)
    {
        User user = new User();
        user = userService.findByUsername(authentication.getName());

        Cart dataCart = new Cart();
        dataCart.setCartid(cartid);

        Product dataProduct = new Product();
        dataProduct.setProductid(productid);

        cartService.delete(user, dataCart, dataProduct);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
