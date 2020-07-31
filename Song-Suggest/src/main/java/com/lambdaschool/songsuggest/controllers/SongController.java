package com.lambdaschool.songsuggest.controllers;

import com.lambdaschool.songsuggest.models.Song;
import com.lambdaschool.songsuggest.models.User;
import com.lambdaschool.songsuggest.services.SongService;
import com.lambdaschool.songsuggest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    @Autowired
    SongService songService;

    @Autowired
    UserService userService;

    @GetMapping(value = "/user/{userid}", produces = {"application/json"})
    public ResponseEntity<?> listAllSongs(HttpServletRequest request, @PathVariable long userid) {
        List<Song> mySongs = songService.findAllByUserId(userid);
        return new ResponseEntity<>(mySongs, HttpStatus.OK);
    }

    @GetMapping(value = "/song/{songId}",
            produces = {"application/json"})
    public ResponseEntity<?> getSongById(HttpServletRequest request,
                                         @PathVariable
                                                 Long songId) {
        Song s = songService.findSongById(songId);
        return new ResponseEntity<>(s,
                HttpStatus.OK);
    }

    @PostMapping(value = "/create/user/{userid}/song")
    public ResponseEntity<?> addNewSong(@PathVariable long userid,
                                        @Valid @RequestBody Song newSong) {
        User dataUser = new User();
        dataUser.setUserid(userid);

        newSong.setSongid(0);
        newSong = songService.save(dataUser, newSong);


        HttpHeaders responseHeaders = new HttpHeaders();
        URI newBookURI = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{songid}")
                .buildAndExpand(newSong.getSongid())
                .toUri();
        responseHeaders.setLocation(newBookURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PutMapping(value = "/update/song/{songid}",
            consumes = "application/json")
    public ResponseEntity<?> updateFullSong(
            @Valid
            @RequestBody
                    Song updateSong,
            @PathVariable
                    long songid) {
//        User dataUser = userService.findUserById(userid);



        updateSong.setSongid(songid);
        songService.update(updateSong, songid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @DeleteMapping(value = "/delete/song/{songid}")
    public ResponseEntity<?> deleteSongById(
            @PathVariable
                    long songid) {
        songService.delete(songid);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
