package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Mop;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMopRepository implements MopRepository {

    private final EntityManager em;

    public JpaMopRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Optional<Mop> findByStage(int stage_val) {
        List<Mop> result = em.createQuery("select m from Mop m where m.stage_val = :stage_val", Mop.class)
                .setParameter("stage_val", stage_val)
                .getResultList();
        return result.stream().findAny();
    }
}
