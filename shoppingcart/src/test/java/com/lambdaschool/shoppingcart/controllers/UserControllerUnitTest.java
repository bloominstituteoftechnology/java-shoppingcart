package com.lambdaschool.shoppingcart.controllers;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import com.lambdaschool.shoppingcart.exceptions.ResourceNotFoundException;
import com.lambdaschool.shoppingcart.services.HelperFunctions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Locale;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunnger.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = FoundationApplication.class)
@AutoConfigureMockMvc
@WithMockUser(username = "admin",
roles = {"USER", "ADMIN"})
public class UserControllerUnitTest
{
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    UserService userService;

    @MockBean
    HelperFunctions helperFunctions;

    List<User> userList;

    @Before
    public void setUp() throws Exception
    {
        userList = new ArrayList<>();

        public void run(String[] args) throws
        Exception
        {
            userService.deleteAll();
            roleService.deleteAll();
            Role r1 = new Role("admin");
            Role r2 = new Role("user");
            Role r3 = new Role("data");

//            r1 = roleService.save(r1);
//            r2 = roleService.save(r2);
//            r3 = roleService.save(r3);

            roleType1.setRoleid(1);
            roleType2.setRoleid(2);
            roleType3.setRoleid(3);

            // admin, data, user
            User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");

            u1.setUserid(10);

            //One to Many
            u1.getRoles()
                .add(new UserRoles(u1,
                    r1));
            u1.getRoles()
                .add(new UserRoles(u1,
                    r2));
            u1.getRoles()
                .add(new UserRoles(u1,
                    r3));

            //One to One
            u1.getUseremails()
                .add(new Useremail(u1,
                    "admin@email.local"));
            u1.getUseremails()
                .add(new Useremail(u1,
                    "admin@mymail.local"));

            u1.getUseremails.get(0).setUseremailId(20);
            u1.getUseremails.get(1).setUseremailId(21);



            // data, user
            User u2 = new User("cinnamon",
                "1234567",
                "cinnamon@lambdaschool.local");

            u2.setUserid(11);
            u2.getRoles()
                .add(new UserRoles(u2,
                    r2));
            u2.getRoles()
                .add(new UserRoles(u2,
                    r3));

            u2.getUseremails()
                .add(new Useremail(u2,
                    "cinnamon@mymail.local"));
            u2.getUseremails()
                .add(new Useremail(u2,
                    "hops@mymail.local"));
            u2.getUseremails()
                .add(new Useremail(u2,
                    "bunny@email.local"));

            u2.getUseremails.get(0).setUserId(22);
            u2.getUseremails.get(1).setUseremailId(23);
            u2.getUseremails.get(2).setUseremailId(24);

            // user
            User u3 = new User("barnbarn",
                "ILuvM4th!",
                "barnbarn@lambdaschool.local");

            u3.setUserid(12);

            u3.getRoles()
                .add(new UserRoles(u3,
                    r2));
            u3.getUseremails()
                .add(new Useremail(u3,
                    "barnbarn@email.local"));

            u3.getUseremails.get(0).setUseremailId(25);

            User u4 = new User("puttat",
                "password",
                "puttat@school.lambda");

            u4.setUserid(13);

            u4.getRoles()
                .add(new UserRoles(u4,
                    r2));


            User u5 = new User("misskitty",
                "password",
                "misskitty@school.lambda");

            u5.setUserid(14);

            u5.getRoles()
                .add(new UserRoles(u5,
                    r2));

            userList.add(u1);
            userList.add(u2);
            userList.add(u3);
            userList.add(u4);
            userList.add(u5);

            if (false)
            {
                // using JavaFaker create a bunch of regular users
                // https://www.baeldung.com/java-faker
                // https://www.baeldung.com/regular-expressions-java

                FakeValuesService fakeValuesService = new FakeValuesService(new Locale("en-US"),
                    new RandomService());
                Faker nameFaker = new Faker(new Locale("en-US"));

                for (int i = 0; i < 25; i++)
                {
                    new User();
                    User fakeUser;

                    fakeUser = new User(nameFaker.name()
                        .username(),
                        "password",
                        nameFaker.internet()
                            .emailAddress());
                    fakeUser.getRoles()
                        .add(new UserRoles(fakeUser,
                            r2));
                    fakeUser.getUseremails()
                        .add(new Useremail(fakeUser,
                            fakeValuesService.bothify("????##@gmail.com")));
                    userService.save(fakeUser);
                }

    }

    @After
    public void tearDown() throws Exception
    {
    }

    @Test
    public void listAllUsers() throws Exception
    {
        String apiUrl = "/users/users";
        Mockito.when(userService.findAll()).thenReturn(userList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String testResult = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = mapper.writeValueAsString(userList);
        assertEquals(expectedResult, testResult);
    }



    @Test
    public void getUserByIdNotFound() throws Exception
    {
        String apiUrl = "/users/users/100";
        Mockito.when(userService.findUserById(100)).thenReturn(null);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String testResult = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String expectedResult = "";
        assertEquals(expectedResult, testResult);
    }
    }



    @Test
    public void getUserByName()
    {
    }

    @Test
    public void getUserLikeName()
    {
    }

    @Test
    public void addNewUser()
    {
        User u3 = new User("barnbarn",
            "ILuvM4th!",
            "barnbarn@lambdaschool.local");

        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        u3.getUseremails()
            .add(new Useremail(u3,
                "barnbarn@email.local"));

        u3.getUseremails.get(0).setUseremailId(20);
        u3.setUserid(13);

        String apiUrl = "/users/user";

        ObjectMapper mapper = new ObjectMapper();
        String userString = mapper.writeValueAsString(u3);

        Mockito.when(userService.save(any(User.class))).thenReturn(u3);

        RequestBuilder rb = MockMvcRequestBuilders.post(apiUrl).accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON)
            .content(userString);
        mockMvc.perform(rb).andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
    }
    }

    @Test
    public void updateFullUser()
    {
    }

    @Test
    public void updateUser()
    {
    }

    @Test
    public void deleteUserById()
    {
    }

    @Test
    public void getCurrentUserInfo()
    {
    }
}
