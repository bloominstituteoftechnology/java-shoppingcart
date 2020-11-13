package com.lambdaschool.shoppingcart.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.shoppingcart.ShoppingCartApplication;
import com.lambdaschool.shoppingcart.models.CartItem;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CartControllerTest
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
    public void listCartItemsByUserId()
    {
        String apiUrl = "/users/user/{userid}";
        Mockito.when(cartItemService.findAll()).thenReturn(cartItemService);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String testResult = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(cartItemList);

        assertEquals(expectedResult, testResult);

        //not sure this probably has extra code since we aren't using mock data like above^^
    }

    @Test
    public void addToCart()
    {
        for (int i = 0; i < 17; i++)
        {
            cartItemService.addToCart(u3.getUserid(),
                p3.getProductid(), "added via seed data");
        }

        CartItem addCartItem = cartItemService.save(p3);
        assertNotNull(addCartItem);
        assertEquals(p3, addCartItem.getProduct());
    }

    @Test
    public void removeFromCart()
    {
        cartItemService.delete(4);
        assertEquals(2, cartItemService.findAll().size());
    }
}