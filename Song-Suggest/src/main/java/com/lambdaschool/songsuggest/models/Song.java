package com.lambdaschool.songsuggest.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "songs")
public class Song extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long songid;

    private String name;
    private String artist;
    private String spotifyid;
    private String album;
    private String previewurl;
    private String albumcover;
    private String releasedate;


    @ManyToOne
    @JoinColumn(name = "userid",
            nullable = false)
    @JsonIgnoreProperties(value = "songs",
            allowSetters = true)
    private User user;

    public Song(String name, String artist, String spotifyid, String album, String previewurl, String albumcover, String releasedate) {
        this.name = name;
        this.artist = artist;
        this.spotifyid = spotifyid;
        this.album = album;
        this.previewurl = previewurl;
        this.albumcover = albumcover;
        this.releasedate = releasedate;
    }

    public Song() {
    }

    public long getSongid() {
        return songid;
    }

    public void setSongid(long songid) {
        this.songid = songid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSpotifyid() {
        return spotifyid;
    }

    public void setSpotifyid(String spotifyid) {
        this.spotifyid = spotifyid;
    }

    public String getAlbum() {
        return album;
    }


    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPreviewurl() {
        return previewurl;
    }

    public void setPreviewurl(String previewurl) {
        this.previewurl = previewurl;
    }

    public String getAlbumcover() {
        return albumcover;
    }

    public void setAlbumcover(String albumcover) {
        this.albumcover = albumcover;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
