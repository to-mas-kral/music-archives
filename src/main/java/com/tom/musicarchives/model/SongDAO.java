package com.tom.musicarchives.model;

import java.util.List;

public interface SongDAO {
    Song getSongById(int id);

    void saveSong(Song song, int id);

    void updateSong(Song song);

    void deleteSong(int id);

    List<Song> getAllSongs();
}
