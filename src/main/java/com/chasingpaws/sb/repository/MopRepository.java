package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Mop;

import java.util.List;
import java.util.Optional;

public interface MopRepository {

    Mop save(Mop mop);
    Optional<Mop> findById(int m_idx);
    Optional<Mop> findByName(String m_name);
    List<Mop> findAll();
}
