package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.models.Song;
import com.lambdaschool.songsuggest.models.User;

import java.util.List;

public interface SongService {
    List<Song> findAllByUserId(Long userid);

    Song findSongById(long id);

    Song save(User user, Song song);

    Song update(Song role, long id);

    void delete(long id);

    void deleteAll();


}
