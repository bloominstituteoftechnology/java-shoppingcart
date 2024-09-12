package com.lambdaschool.shoppingcart.controllers;

import com.lambdaschool.shoppingcart.models.CartItem;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.services.CartItemService;
import com.lambdaschool.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController
{
    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/user",
        produces = {"application/json"})
    public ResponseEntity<?> listCartItemsByUserId()
    {
        User u = userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication()
            .getName());
        return new ResponseEntity<>(u,
            HttpStatus.OK);
    }

    @PutMapping(value = "/add/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> addToCart(
        @PathVariable
            long productid)
    {
        long userid = userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication()
            .getName())
            .getUserid();

        CartItem addCartTtem = cartItemService.addToCart(userid,
            productid,
            "I am not working");
        return new ResponseEntity<>(addCartTtem,
            HttpStatus.OK);
    }

    @DeleteMapping(value = "/remove/product/{productid}",
        produces = {"application/json"})
    public ResponseEntity<?> removeFromCart(
        @PathVariable
            long productid)
    {
        long userid = userService.findByName(SecurityContextHolder.getContext()
            .getAuthentication()
            .getName())
            .getUserid();

        CartItem removeCartItem = cartItemService.removeFromCart(userid,
            productid,
            "I am still not working");
        return new ResponseEntity<>(removeCartItem,
            HttpStatus.OK);
    }
}
