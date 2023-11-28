package com.tom.musicarchives.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
}
