package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Skill;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaSkillRepository implements SkillRepository {
    private final EntityManager em;

    public JpaSkillRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Skill> findById(int s_idx) {
        return Optional.empty();
    }

    @Override
    public Optional<Skill> findByName(String s_name) {
        return Optional.empty();
    }

    @Override
    public List<Skill> findAllById(int c_idx) {
        return em.createQuery("select s from Skill s where c_idx = :c_idx", Skill.class)
                .setParameter("c_idx", c_idx)
                .getResultList();
    }
}
