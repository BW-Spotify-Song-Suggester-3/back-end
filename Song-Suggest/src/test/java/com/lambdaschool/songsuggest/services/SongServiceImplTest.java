package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.SongsuggestApplication;
import com.lambdaschool.songsuggest.exceptions.ResourceNotFoundException;
import com.lambdaschool.songsuggest.models.*;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsuggestApplication.class)
//**********
// Note security is handled at the controller, hence we do not need to worry about security here!
//**********
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongServiceImplTest {

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

        List<Song> myList = songService.findAll();
        for (Song u: myList)
        {
            System.out.println(u.getSongid() + " " + u.getName());
        }

    }

    @After
    public void tearDown() throws
            Exception
    {
    }

    @Test
    public void findAllByUserId() {
    }

    @Test
    public void findSongById() {
        assertEquals("jammin", songService.findSongById(26).getName());
    }
    @Test(expected = ResourceNotFoundException.class)
    public void notfindSongById() {
        assertEquals("jammin", songService.findSongById(26).getName());
    }

    @Test
    public void save() {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

//        Song s1 = new Song("Jammin", "Bob Marley", "982", "Exodus", "www.previewurl.com", "www.albumcoverurl.com", "June 3, 1977", u1);

        User u2 = new User("mike",
                "password",
                "mike@lambdaschool.local");
        u2.getRoles()
                .add(new UserRoles(u2, r1));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "admin@email.local"));
        u2.getUseremails()
                .add(new Useremail(u2,
                        "admin@mymail.local"));


        userService.save(u2);

        String troy = "troy";
        Song s1 = new Song(troy, "Lane 8", "5kDeYoYdLnW8ILTXdbiXjJ", "Little by Little", "troy.com", "https://i.scdn.co/image/ab67616d00001e0211fbdc2cde3fc1aeac48c970", "2018-01-19");

        Song addSong = songService.save(u2, s1);
        assertNotNull(addSong);
        assertEquals(troy, addSong.getName());
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void deleteAll() {
        songService.deleteAll();
        assertEquals(0, songService.findAll().size());
    }
}