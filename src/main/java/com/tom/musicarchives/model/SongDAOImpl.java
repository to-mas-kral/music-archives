package com.tom.musicarchives.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SongDAOImpl implements SongDAO {
    private final EntityManager entityManager;

    @Autowired
    public SongDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Song getSongById(int id) {
        return entityManager.find(Song.class, id);
    }

    @Override
    @Transactional
    public void saveSong(Song song, int album_id) {
        Album album = entityManager.getReference(Album.class, album_id);
        song.setAlbum(album);
        entityManager.persist(song);
    }

    @Override
    @Transactional
    public void updateSong(Song song) {
        entityManager.merge(song);
    }

    @Override
    @Transactional
    public void deleteSong(int id) {
        var song = entityManager.find(Song.class, id);
        entityManager.remove(song);
    }

    @Override
    public List<Song> getAllSongs() {
        var query = entityManager.createQuery("FROM Song", Song.class);
        return query.getResultList();
    }
}
