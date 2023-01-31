package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Item;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JpaItemRepository implements ItemRepository{

    private final EntityManager em;

    public JpaItemRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Item> findById(int i_idx) {
        Item item = em.find(Item.class, i_idx);
        return Optional.ofNullable(item);
    }

    @Override
    public List<Item> findAll() {
        return em.createQuery("select i from Character i", Item.class)
                .getResultList();
    }

    @Override
    public List<Item> findAllByStage(Map map) {
        return em.createQuery("select i from Item i where i.i_class = :i_class and i_level = :i_level", Item.class)
                .setParameter("i_class", map.get("i_class"))
                .setParameter("i_level", map.get("i_level"))
                .getResultList();
    }
}
