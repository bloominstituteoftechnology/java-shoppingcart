package com.lambdaschool.shoppingcart.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartItemId implements Serializable
{
    private long user;

    private long product;

    public CartItemId()
    {
    }

    public CartItemId(
        long user,
        long product)
    {
        this.user = user;
        this.product = product;
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getProduct()
    {
        return product;
    }

    public void setProduct(long product)
    {
        this.product = product;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CartItemId that = (CartItemId) o;
        return user == that.user &&
            product == that.product;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
