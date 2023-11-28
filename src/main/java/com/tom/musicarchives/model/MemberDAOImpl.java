package com.tom.musicarchives.model;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberDAOImpl implements MemberDAO {
    private final EntityManager entityManager;

    @Autowired
    public MemberDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Member getMemberById(int id) {
        return entityManager.find(Member.class, id);
    }

    @Override
    @Transactional
    public void saveMember(Member member) {
        entityManager.persist(member);
    }

    @Override
    @Transactional
    public void updateMember(Member member) {
        entityManager.merge(member);
    }

    @Override
    @Transactional
    public void deleteMember(int id) {
        var member = entityManager.find(Member.class, id);
        entityManager.remove(member);
    }

    @Override
    public List<Member> getAllMembers() {
        var query = entityManager.createQuery("FROM Member", Member.class);
        return query.getResultList();
    }
}
