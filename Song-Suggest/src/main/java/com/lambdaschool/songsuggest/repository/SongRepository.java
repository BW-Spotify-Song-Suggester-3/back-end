package com.lambdaschool.songsuggest.repository;

import com.lambdaschool.songsuggest.models.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {
    List<Song> findAllByUser_Userid(long id);
}
