package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.exceptions.ResourceFoundException;
import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.repositories.CartRepository;
import com.lambdaschool.shoppingcart.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service(value = "productService")
public class ProductServiceImpl
        implements ProductService
{
    /**
     * Connects this service to the product repository
     */
    @Autowired
    private ProductRepository productrepos;

    @Autowired
    private CartRepository cartrepos;

    /**
     * Connects this service to the auditing service in order to find the current user
     */
    @Autowired
    private UserAuditing userAuditing;

    @Override
    public List<Product> findAll()
    {
        List<Product> list = new ArrayList<>();
        /*
         * findAll returns an iterator set.
         * iterate over the iterator set and add each element to an array list.
         */
        productrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Product findProductById(long id)
    {
        return productrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " not found!"));
    }

    @Transactional
    @Override
    public void delete(long id)
    {
        productrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " not found!"));
        productrepos.deleteById(id);
        cartrepos.removeCartWithNoProducts();
    }

    @Transactional
    @Override
    public Product save(Product product)
    {
        if (product.getCarts()
                .size() > 0)
        {
            throw new ResourceFoundException("Carts are not updated through Products");
        }
        ;

        return productrepos.save(product);
    }

    @Transactional
    @Override
    public Product update(long id,
                          Product product)
    {
        if (product.getCarts()
                .size() > 0)
        {
            throw new ResourceFoundException("Carts cannot be updated through this process");
        }

        Product currentProduct = productrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product id " + id + " not found!"));

        if (product.getName() != null)
        {
            currentProduct.setName(product.getName());
        }

        if (product.hasprice)
        {
            currentProduct.setPrice(product.getPrice());
        }

        if (product.getDescription() != null)
        {
            currentProduct.setDescription(product.getDescription());
        }

        if (product.getComments() != null)
        {
            currentProduct.setComments(product.getComments());
        }

        productrepos.updateProductInformation(userAuditing.getCurrentAuditor()
                                                      .get(),
                                              id,
                                              currentProduct.getName(),
                                              currentProduct.getPrice(),
                                              currentProduct.getDescription(),
                                              currentProduct.getComments());
        return findProductById(id);
    }
}
