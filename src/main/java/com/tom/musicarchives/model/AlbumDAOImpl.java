package com.tom.musicarchives.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumDAOImpl implements AlbumDAO {
    private final EntityManager entityManager;

    @Autowired
    public AlbumDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Album getAlbumById(int id) {
        return entityManager.find(Album.class, id);
    }

    @Override
    @Transactional
    public void saveAlbum(Album album, int band_id) {
        Band band = entityManager.getReference(Band.class, band_id);
        album.setBand(band);

        entityManager.persist(album);
    }

    @Override
    @Transactional
    public void updateAlbum(Album album) {
        entityManager.merge(album);
    }

    @Override
    @Transactional
    public void deleteAlbum(int id) {
        var album = entityManager.find(Album.class, id);
        entityManager.remove(album);
    }

    @Override
    public List<Album> getAllAlbums() {
        var query = entityManager.createQuery("FROM Album", Album.class);
        return query.getResultList();
    }
}
