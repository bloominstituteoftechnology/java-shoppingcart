package com.lambdaschool.shoppingcart.services;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

//use database

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ShoppingCartApplication.class)
public class UserServiceImplUnitTest
{
    @Autowired
    UserService userService;

    @Before
    public void setUp() throws Exception
    {
        //mock -> fake data
        //stubs -> fake method
        //Java mock = stub -> mocks
        MockitoAnnotations.initMocks(this);

        List<User> myList = userService.findAll();
        for (User u : myList) {
            System.out.println(u.getUserid() + " " + u.getName());
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void findUserById()
    {
    }

    @Test
    public void findByNameContaining()
    {
        assertEquals();
    }

    @Test
    public void findAll()
    {
    }

    @Test
    public void delete()
    {
    }

    @Test
    public void findByName()
    {
    }

    @Test
    public void save()
    {
    }

    @Test
    public void update()
    {
    }

    @Test
    public void deleteAll()
    {
    }
}
