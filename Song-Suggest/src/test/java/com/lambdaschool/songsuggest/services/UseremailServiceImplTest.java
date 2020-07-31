package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.SongsuggestApplication;
import com.lambdaschool.songsuggest.models.Role;
import com.lambdaschool.songsuggest.models.Useremail;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsuggestApplication.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UseremailServiceImplTest {
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

        List<Useremail> myList = useremailService.findAll();
        for (Useremail u: myList)
        {
            System.out.println(u.getUseremailid() + " " + u.getUseremail());
        }

    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @org.junit.Test
    public void a_findAll() {
        assertEquals(2, useremailService.findAll().size());
    }

    @org.junit.Test
    public void b_findUseremailById() {
        assertEquals("admin@email.local", useremailService.findUseremailById(5).getUseremail());
    }

    @org.junit.Test
    public void c_delete() {
        useremailService.delete(6);
        Assert.assertEquals(1, useremailService.findAll().size());
    }

    @org.junit.Test
    public void update() {
    }

    @org.junit.Test
    public void save() {
    }
}