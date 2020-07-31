package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.SongsuggestApplication;
import com.lambdaschool.songsuggest.models.User;
import com.lambdaschool.songsuggest.models.Useremail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsuggestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class UserServiceImplTest {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UseremailService useremailService;

    @Autowired UserService userService;



    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

        List<User> myList = userService.findAll();
        for (User u: myList)
        {
            System.out.println(u.getUserid() + " " + u.getUsername());
        }

    }

    @After
    public void tearDown() throws
            Exception
    {
    }


    @Test
    void findUserById() {
        assertEquals("admin", userService.findUserById(4).getUsername());
    }

    @Test
    void findByNameContaining() {
        assertEquals(1, userService.findByNameContaining("ad").size());
    }

    @Test
    void findAll() {
        assertEquals(1, userService.findAll().size());
    }

    @Test
    void delete() {
    }

    @Test
    void findByName() {
        Assert.assertEquals("admin", userService.findByName("admin").getUsername());
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void z_deleteAll() {
        userService.deleteAll();
        Assert.assertEquals(0, userService.findAll().size());
    }
}