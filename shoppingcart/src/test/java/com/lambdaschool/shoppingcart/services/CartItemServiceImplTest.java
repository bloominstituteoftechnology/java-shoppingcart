package com.lambdaschool.shoppingcart.services;

import com.lambdaschool.shoppingcart.ShoppingCartApplication;
import com.lambdaschool.shoppingcart.models.CartItem;
import com.lambdaschool.shoppingcart.repository.CartItemRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartItemServiceImplTest
{
    @Autowired
    CartItemService cartItemService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void addToCart() throws Exception
    {
        for (int i = 0; i < 17; i++)
        {
            cartItemService.addToCart(u3.getUserid(),
                p3.getProductid(), "added via seed data");
        }

        CartItem addCartItem = cartItemService.save(p3);
        assertNotNull(addCartItem);
        assertEquals(p3, addCartItem.getProduct());

    @Test
    public void removeFromCart() throws Exception
    {
        cartItemService.delete(4);
        assertEquals(2, cartItemService.findAll().size());
    }
}