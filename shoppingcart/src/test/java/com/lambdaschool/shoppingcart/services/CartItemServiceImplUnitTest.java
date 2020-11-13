package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingCartApplication;
import com.lambdaschool.shoppingcart.models.Product;
import com.lambdaschool.shoppingcart.models.User;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartItemServiceImplUnitTest {

//    @Autowired
//    ProductService productService;
//
//    @Autowired
//    UserService userService;

    @Autowired
    CartItemService cartItemService;

    @Before
    public void setUp() throws Exception {
        //mock -> fake data
        //stubs -> fake method
        //Java mock = stub -> mocks

        MockitoAnnotations.initMocks(this);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addToCart() {

        //User testUser = userService.findUserById(2);
        //cinnamon user id is 2

        //assertEquals(5, cartItemService.addToCart(3,6, "Added").getQuantity());
        assertEquals(5,
                cartItemService.addToCart(3,
                        6,
                        "Hello")
                        .getQuantity());

    }

    @Test
    public void removeFromCart() {
    }
}