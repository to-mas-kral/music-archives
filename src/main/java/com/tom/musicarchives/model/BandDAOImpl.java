package com.tom.musicarchives.model;

import com.tom.musicarchives.utils.BandSearchQuery;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class BandDAOImpl implements BandDAO {
    private final EntityManager entityManager;

    @Autowired
    public BandDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Band getBandById(int id) {
        return entityManager.find(Band.class, id);
    }

    @Override
    @Transactional
    public void saveBand(Band band) {
        entityManager.persist(band);
    }

    @Override
    @Transactional
    public void updateBand(Band band) {
        entityManager.merge(band);
    }

    @Override
    @Transactional
    public void deleteBand(int id) {
        var band = entityManager.find(Band.class, id);
        entityManager.remove(band);
    }

    @Override
    public List<Band> getAllBands() {
        var query = entityManager.createQuery("FROM Band", Band.class);
        return query.getResultList();
    }

    @Override
    public List<Album> getBandAlbums(int band_id) {
        var query = entityManager.createQuery("FROM Album a WHERE a.band.id=:band_id", Album.class);
        query.setParameter("band_id", band_id);
        return query.getResultList();
    }

    @Override
    public List<Member> getBandMembers(int band_id) {
        var band = getBandById(band_id);
        var query = entityManager.createQuery("SELECT m FROM Band b JOIN b.members m WHERE b.id = :band_id", Member.class);
        query.setParameter("band_id", band_id);
        return query.getResultList();
    }

    @Override
    public List<Band> searchBands(BandSearchQuery bandSearchQuery) {
        var query = entityManager.createQuery("SELECT b FROM Band b WHERE b.genre LIKE CONCAT('%',:genre,'%') AND b.country LIKE CONCAT('%',:country,'%')", Band.class);
        query.setParameter("genre", bandSearchQuery.getGenre());
        query.setParameter("country", bandSearchQuery.getCountry());
        return query.getResultList();
    }

    // https://stackoverflow.com/a/21432374/18028230
    @Override
    public Band getRandomBand() {
        var countQuery = entityManager.createNativeQuery("SELECT COUNT(*) FROM band");
        long count = (Long) countQuery.getSingleResult();

        Random random = new Random();
        int number = random.nextInt((int) count);

        var selectQuery = entityManager.createQuery("SELECT b FROM Band b", Band.class);
        selectQuery.setFirstResult(number);
        selectQuery.setMaxResults(1);
        return selectQuery.getSingleResult();
    }
}
