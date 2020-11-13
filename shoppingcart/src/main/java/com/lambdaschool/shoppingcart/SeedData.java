package com.lambdaschool.shoppingcart;

import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.models.Role;
import com.lambdaschool.shoppingcart.models.User;
import com.lambdaschool.shoppingcart.models.UserRoles;
import com.lambdaschool.shoppingcart.services.CartItemService;
import com.lambdaschool.shoppingcart.services.ProductService;
import com.lambdaschool.shoppingcart.services.RoleService;
import com.lambdaschool.shoppingcart.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * SeedData puts both known and random data into the database. It implements CommandLineRunner.
 * <p>
 * CoomandLineRunner: Spring Boot automatically runs the run method once and only once
 * after the application context has been loaded.
 */
@Transactional
@Component
public class SeedData
    implements CommandLineRunner
{
    /**
     * Connects the Role Service to this process
     */
    @Autowired
    RoleService roleService;

    /**
     * Connects the user service to this process
     */
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Autowired
    CartItemService cartItemService;

    /**
     * Generates test, seed data for our application
     *
     * @param args The parameter is required by the parent interface but is not used in this process.
     */
    @Transactional
    @Override
    public void run(String[] args) throws
                                   Exception
    {
        // Adding users

        Role r1 = new Role("admin");
        Role r2 = new Role("user");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);

        User u1 = new User("barnbarn",
            "LambdaLlama",
            "barnbarn@host.local",
            "added via seed data");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));

        u1 = userService.save(u1);

        User u2 = new User("cinnamon",
            "LambdaLlama",
            "cinnamon@host.local",
            "added via seed data");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));

        u2 = userService.save(u2);

        User u3 = new User("stumps",
            "LambdaLlama",
            "stumps@host.local",
            "added via seed data");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));

        u3 = userService.save(u3);

        // Adding Products

        Product p1 = new Product();
        p1.setName("PEN");
        p1.setDescription("MAKES WORDS");
        p1.setPrice(2.50);
        p1.setComments("added via seed data");

        Product p2 = new Product();
        p2.setName("PENCIL");
        p2.setDescription("DOES MATH");
        p2.setPrice(1.50);
        p2.setComments("added via seed data");

        Product p3 = new Product();
        p3.setName("COFFEE");
        p3.setDescription("EVERYONE NEEDS COFFEE");
        p3.setPrice(4.00);
        p3.setComments("added via seed data");

        p1 = productService.save(p1);
        p2 = productService.save(p2);
        p3 = productService.save(p3);

        // Creating Carts
        for (int i = 0; i < 4; i++)
        {
            cartItemService.addToCart(u1.getUserid(),
                p1.getProductid(), "added via seed data");
        }

        for (int i = 0; i < 3; i++)
        {
            cartItemService.addToCart(u1.getUserid(),
                p2.getProductid(), "added via seed data");
        }

        for (int i = 0; i < 2; i++)
        {
            cartItemService.addToCart(u1.getUserid(),
                p3.getProductid(), "added via seed data");
        }

        for (int i = 0; i < 1; i++)
        {
            cartItemService.addToCart(u2.getUserid(),
                p3.getProductid(), "added via seed data");
        }

        for (int i = 0; i < 17; i++)
        {
            cartItemService.addToCart(u3.getUserid(),
                p3.getProductid(), "added via seed data");
        }

    }
}
