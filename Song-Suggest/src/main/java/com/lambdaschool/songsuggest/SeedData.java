package com.lambdaschool.songsuggest;

import com.lambdaschool.songsuggest.models.*;
import com.lambdaschool.songsuggest.services.RoleService;
import com.lambdaschool.songsuggest.services.SongService;
import com.lambdaschool.songsuggest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Component
public class SeedData
        implements CommandLineRunner {

    @Autowired
    RoleService roleService;


    @Autowired
    UserService userService;

    @Autowired
    SongService songService;


    @Transactional
    @Override
    public void run(String[] args) throws
            Exception {
        userService.deleteAll();
        roleService.deleteAll();
        songService.deleteAll();


        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

//        Song s1 = new Song("Jammin", "Bob Marley", "982", "Exodus", "www.previewurl.com", "www.albumcoverurl.com", "June 3, 1977", u1);

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


        userService.save(u1);

//        Song s1 = new Song("Jammin", "Bob Marley", "982", "Exodus", "www.previewurl.com", "www.albumcoverurl.com", "June 3, 1977", u1);
//        s1 = songService.save(u1, s1);

    }
}