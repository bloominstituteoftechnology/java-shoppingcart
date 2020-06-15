package com.lambdaschool.shoppingcart.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = "hasprice")
public class Product
        extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productid;

    @Column(nullable = false,
            unique = true)
    private String name;

    @Transient
    public boolean hasprice = false;
    private double price;

    private String description;
    private String comments;

    @OneToMany(mappedBy = "product",
            cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = "product",
            allowSetters = true)
    private List<CartItem> carts = new ArrayList<>();

    public Product()
    {

    }

    public long getProductid()
    {
        return productid;
    }

    public void setProductid(long productid)
    {
        this.productid = productid;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public double getPrice()
    {
        return price;
    }

    public void setPrice(double price)
    {
        hasprice = true;
        this.price = price;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public List<CartItem> getCarts()
    {
        return carts;
    }

    public void setCarts(List<CartItem> carts)
    {
        this.carts = carts;
    }
}
