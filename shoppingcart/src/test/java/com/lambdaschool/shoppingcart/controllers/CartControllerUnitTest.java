package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.models.*;
import com.lambdaschool.shoppingcart.services.CartItemService;
import com.lambdaschool.shoppingcart.services.UserService;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@WebMvcTest(value = CartController.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin",
roles = {"USER", "ADMIN"})
public class CartControllerUnitTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartItemService cartItemService;

    @MockBean
    private UserService userService;

    private List<User> userList = new ArrayList<>();
    private List<Product> prodList = new ArrayList();

    @Before
    public void setUp() throws Exception {
        Role r1 = new Role("admin");
        r1.setRoleid(1);
        Role r2 = new Role("user");
        r2.setRoleid(2);
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
        u1.setUserid(1);
        User u2 = new User("cinnamon",
                "LambdaLlama",
                "cinnamon@host.local",
                "added via seed data");
        u2.getRoles()
                .add(new UserRoles(u2,
                        r2));
        u2.setUserid(2);
        User u3 = new User("stumps",
                "LambdaLlama",
                "stumps@host.local",
                "added via seed data");
        u3.getRoles()
                .add(new UserRoles(u3,
                        r2));
        u3.setUserid(3);
        // Adding Products
        Product p1 = new Product();
        p1.setProductid(1);
        p1.setName("PEN");
        p1.setDescription("MAKES WORDS");
        p1.setPrice(2.50);
        p1.setComments("added via seed data");
        Product p2 = new Product();
        p1.setProductid(2);
        p2.setName("PENCIL");
        p2.setDescription("DOES MATH");
        p2.setPrice(1.50);
        p2.setComments("added via seed data");
        Product p3 = new Product();
        p1.setProductid(3);
        p3.setName("COFFEE");
        p3.setDescription("EVERYONE NEEDS COFFEE");
        p3.setPrice(4.00);
        p3.setComments("added via seed data");
        prodList.add(p1);
        prodList.add(p2);
        prodList.add(p3);
        // Creating Carts
        CartItem cart1 = new CartItem();
        cart1.setUser(u1);
        cart1.setProduct(p1);
        cart1.setComments("added via seed data");
        cart1.setQuantity(4);
        u1.getCarts()
                .add(cart1);
        CartItem cart2 = new CartItem();
        cart2.setUser(u1);
        cart2.setProduct(p2);
        cart2.setComments("added via seed data");
        cart2.setQuantity(3);
        u1.getCarts()
                .add(cart2);
        CartItem cart3 = new CartItem();
        cart3.setUser(u1);
        cart3.setProduct(p3);
        cart3.setComments("added via seed data");
        cart3.setQuantity(2);
        u1.getCarts()
                .add(cart3);
        CartItem cart4 = new CartItem();
        cart4.setUser(u2);
        cart4.setProduct(p3);
        cart4.setComments("added via seed data");
        cart4.setQuantity(1);
        u2.getCarts()
                .add(cart4);
        CartItem cart5 = new CartItem();
        cart5.setUser(u3);
        cart5.setProduct(p3);
        cart5.setComments("added via seed data");
        cart5.setQuantity(17);
        u3.getCarts()
                .add(cart5);
        userList.add(u1);
        userList.add(u2);
        userList.add(u3);
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void listCartItemsByUserId() {
        String apiUrl = "/carts/user";
        Mockito.when(userService.findByName(any(String.class)))
                .thenReturn(userList.get(0));
        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb)
                .andReturn();
        String tr = r.getResponse()
                .getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(userList.get(0));
        assertEquals(er,
                tr);
    }

    @Test
    public void addToCart() {
    }

    @Test
    public void removeFromCart() {
    }
}