package com.lambdaschool.songsuggest.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lambdaschool.songsuggest.SongsuggestApplication;
import com.lambdaschool.songsuggest.models.*;
import com.lambdaschool.songsuggest.services.SongService;
import com.lambdaschool.songsuggest.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SongsuggestApplication.class)
@WithMockUser(username = "admin", roles = {"ADMIN", "DATA"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SongControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private SongService songService;

    List<Song> songsList = new ArrayList<>();

    @Before
    public void setUp() throws
            Exception
    { mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .apply(SecurityMockMvcConfigurers.springSecurity())
            .build();
        songsList = new ArrayList<>();
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1.setRoleid(9);


        r2.setRoleid(19);


        r3.setRoleid(92);


        User u1 = new User("admin",
                "password",
                "admin@lambdaschool.local");
        u1.getRoles()
                .add(new UserRoles(u1, r1));
        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@email.local"));
        u1.getUseremails()
                .add(new Useremail(u1,
                        "admin@mymail.local"));
        u1.getSongs()
                .add(new Song("troy",
                        "admin@mymail.local", "troy", "Exodus", "troy@gmail.com", "troy@gmail.com", "1991"));
        u1.getSongs().get(0).setSongid(20);
        u1.setUserid(91);

        Song s2 = new Song("mike",
                "mike@mymail.local", "troyre", "Exoduse", "trsoy@gmail.com", "trsoy@gmail.com", "1941");

        s2.setSongid(93);
        songsList.add(s2);


    }

    @After
    public void tearDown() throws
            Exception
    {
    }


    @Test
    public void listAllSongs() throws Exception {
        String apiUrl = "/songs/user/91";
        Mockito.when(songService.findAll()).thenReturn(songsList);

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(songsList);

        assertEquals(er, tr);
    }

    @Test
    public void getSongById() throws Exception
    {
        String apiUrl = "/songs/song/93";
        Mockito.when(songService.findSongById(93)).thenReturn(songsList.get(0));

        RequestBuilder rb = MockMvcRequestBuilders.get(apiUrl).accept(MediaType.APPLICATION_JSON);
        MvcResult r = mockMvc.perform(rb).andReturn();
        String tr = r.getResponse().getContentAsString();

        ObjectMapper mapper = new ObjectMapper();
        String er = mapper.writeValueAsString(songsList.get(0));

        assertEquals(er, tr);
    }

    @Test
    public void addNewSong() {
    }

    @Test
    public void updateFullSong() {
    }

    @Test
    public void z_deleteSongById() throws Exception
    {
        String apiUrl = "/songs/delete/song/{songid}";

        RequestBuilder rb = MockMvcRequestBuilders.delete(apiUrl, "93")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}