package com.tom.musicarchives.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AlbumDAO {
    Album getAlbumById(int id);

    void saveAlbum(Album album, int band_id);

    void updateAlbum(Album album);

    void deleteAlbum(int id);

    List<Album> getAllAlbums();
}
