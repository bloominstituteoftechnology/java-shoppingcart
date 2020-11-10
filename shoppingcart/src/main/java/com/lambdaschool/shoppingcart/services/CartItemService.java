package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.models.CartItem;

public interface CartItemService
{
    CartItem addToCart(
        long userid,
        long productid,
        String comment);

    CartItem removeFromCart(
        long userid,
        long productid,
        String comment);
}
