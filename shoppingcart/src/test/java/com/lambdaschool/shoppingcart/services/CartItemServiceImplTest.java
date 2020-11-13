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
public class CartItemServiceImplTest
{
    @Autowired
    private CartItemService cartItemService;

    @MockBean
    private CartItemRepository cartItemrepos;

    @MockBean
    HelperFunctions helperFunctions;

    private List<CartItem> cartItemList;


    @Before
    public void setUp() throws Exception
    {
        cartItemList = new ArrayList<>();

        Item i1 = new Item("book1");
        i1.setItemid(1);
        Item i2 = new Item("book2");
        i2.setItemid(2);
        Item i3 = new Item("book3");
        i3.setItemid(3);

        User u1 = new User("testuser");
        u1.getItems()
            .add(new UserItems(u1,
                i1));
        u1.getItems()
            .add(new UserItems(u1,
                i2));
        u1.getItems()
            .add(new UserItems(u1,
                i3));

        u1.setUserid(101);
        cartItemList.add(u1);

        MockitoAnnotations.initMocks(this);
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void addToCart() throws Exception
    {
        String apiUrl = "/users/user/{itemid}";

        Mockito.when(cartItemService.update(any(CartItem.class),
            any(Long.class)))
            .thenReturn(cartItemList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.put(apiUrl,
            100L)
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .content("{\"cartitem\": \"book1\"}");

        mockMvc.perform(rb)
            .andExpect(status().is2xxSuccessful())
            .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void removeFromCart() throws Exception
    {
        String apiUrl = "/users/user/{itemid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl,
            "3")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
            .andExpect
    }
}