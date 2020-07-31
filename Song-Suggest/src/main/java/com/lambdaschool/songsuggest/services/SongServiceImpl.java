package com.lambdaschool.songsuggest.services;

import com.lambdaschool.songsuggest.exceptions.ResourceNotFoundException;
import com.lambdaschool.songsuggest.models.Song;
import com.lambdaschool.songsuggest.models.User;
import com.lambdaschool.songsuggest.repository.SongRepository;
import com.lambdaschool.songsuggest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service("songService")
public class SongServiceImpl implements SongService {
    @Autowired
    UserAuditing UserAuditing;

    @Autowired
    SongRepository songrepos;

    @Autowired
    UserRepository userrepos;

    @Override
    public List<Song> findAll()
    {
        List<Song> list = new ArrayList<>();
        songrepos.findAll()
                .iterator()
                .forEachRemaining(list::add);
        return list;
    }

    @Override
    public List<Song> findAllByUserId(Long userid) {
        return songrepos.findAllByUser_Userid(userid);
    }

    @Override
    public Song findSongById(long id) {
        return songrepos.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Song id " + id + " not found!"));
    }

    @org.springframework.transaction.annotation.Transactional
    @Override
    public Song save(User user, Song song) {
        Song newSong = new Song();

        User dbuser = userrepos.findById(user.getUserid())
                .orElseThrow(() -> new ResourceNotFoundException("User id " + user.getUserid() + " not found"));
        newSong.setUser(dbuser);


        newSong.setName(song.getName());
        newSong.setArtist(song.getArtist());
        newSong.setSpotifyid(song.getSpotifyid());
        newSong.setAlbum(song.getAlbum());
        newSong.setPreviewurl(song.getPreviewurl());
        newSong.setAlbumcover(song.getAlbumcover());
        newSong.setReleasedate(song.getReleasedate());


        return songrepos.save(newSong);
    }

    @Transactional
    @Override
    public Song update(Song song,
                       long id) {
        Song currentSong = findSongById(id);

        if (song.getName() != null) {
            currentSong.setName(song.getName());
        }

        if (song.getArtist() != null) {
            currentSong.setArtist(song.getArtist());
        }

        if (song.getSpotifyid() != null) {
            currentSong.setSpotifyid(song.getSpotifyid());
        }

        if (song.getAlbum() != null) {
            currentSong.setAlbum(song.getAlbum());
        }

        if (song.getPreviewurl() != null) {
            currentSong.setPreviewurl(song.getPreviewurl());
        }

        if (song.getAlbumcover() != null) {
            currentSong.setAlbumcover(song.getAlbumcover());
        }

        if (song.getReleasedate() != null) {
            currentSong.setReleasedate(song.getReleasedate());
        }

        return songrepos.save(currentSong);
    }

    @Transactional
    @Override
    public void delete(long id) {
        if (songrepos.findById(id)
                .isPresent()) {
            songrepos.deleteById(id);
        } else {
            throw new EntityNotFoundException("Song with id " + id + " Not Found!");
        }
    }

    @Transactional
    @Override
    public void deleteAll() {
        songrepos.deleteAll();
    }

}
