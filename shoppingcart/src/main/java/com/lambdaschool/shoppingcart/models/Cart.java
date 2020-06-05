package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "cart",
            allowSetters = true)
    private List<CartItem> products = new ArrayList<>();

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
