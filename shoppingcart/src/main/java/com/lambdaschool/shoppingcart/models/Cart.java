package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long cartid;

    @OneToMany(mappedBy = "cart",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    @JsonIgnoreProperties(value = "cart",
            allowSetters = true)
    private Set<CartItem> products = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "carts",
            allowSetters = true)
    private User user;

    public Cart()
    {

    }

    public long getCartid()
    {
        return cartid;
    }

    public void setCartid(long cartid)
    {
        this.cartid = cartid;
    }

    public List<CartItem> getProducts()
    {
        return products;
    }

    public void setProducts(List<CartItem> products)
    {
        this.products = products;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
