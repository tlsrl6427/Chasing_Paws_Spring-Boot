package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Skill;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaCharacterRepository implements CharacterRepository{

    private final EntityManager em;

    public JpaCharacterRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Character> findById(int c_idx) {
        Character character = em.find(Character.class, c_idx);
        return Optional.ofNullable(character);
    }

    @Override
    public Optional<Character> findByName(String c_name) {
        List<Character> result = em.createQuery("select c from Character c where c.c_name = :c_name", Character.class)
                .setParameter("c_name", c_name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Character> findAll() {
        return em.createQuery("select c from Character c", Character.class)
                .getResultList();
    }
}
