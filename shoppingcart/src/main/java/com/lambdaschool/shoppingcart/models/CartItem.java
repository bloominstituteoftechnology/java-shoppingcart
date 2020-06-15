package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cartitems")
public class CartItem
        extends Auditable
        implements Serializable
{
    @Id
    @ManyToOne
    @JoinColumn(name = "cartid")
    @JsonIgnoreProperties(value = "products")
    private Cart cart;

    @Id
    @ManyToOne
    @JoinColumn(name = "productid")
    @JsonIgnoreProperties(value = "carts")
    private Product product;

    private long quantity;
    private String comments;

    public CartItem()
    {

    }

    public Cart getCart()
    {
        return cart;
    }

    public void setCart(Cart cart)
    {
        this.cart = cart;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public long getQuantity()
    {
        return quantity;
    }

    public void setQuantity(long quantity)
    {
        this.quantity = quantity;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
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
        CartItem cart_item = (CartItem) o;
        return quantity == cart_item.quantity &&
                cart.equals(cart_item.cart) &&
                product.equals(cart_item.product) &&
                Objects.equals(comments,
                               cart_item.comments);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(cart,
                            product,
                            quantity,
                            comments);
    }
}
