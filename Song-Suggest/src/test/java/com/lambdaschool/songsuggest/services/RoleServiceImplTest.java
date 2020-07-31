package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.SongsuggestApplication;
import com.lambdaschool.songsuggest.models.Role;
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

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsuggestApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RoleServiceImplTest {
    @Autowired
    private RoleService roleService;

    @Autowired
    private SongService songService;

    @Autowired UserService userService;



    @Before
    public void setUp() throws
            Exception
    {
        MockitoAnnotations.initMocks(this);

        List<Role> myList = roleService.findAll();
        for (Role u: myList)
        {
            System.out.println(u.getRoleid() + " " + u.getName());
        }

    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void findAll() {
        assertEquals(3, roleService.findAll().size());
    }

    @Test
    public void findRoleById() {
        assertEquals("ADMIN", roleService.findRoleById(1).getName());
    }

    @Test
    public void findByName() {
        assertEquals("ADMIN", roleService.findByName("ADMIN")
                .getName());
    }

    @Test
    public void save() {
    }

    @Test
    public void deleteAll() {
    }

    @Test
    public void update() {
    }
}