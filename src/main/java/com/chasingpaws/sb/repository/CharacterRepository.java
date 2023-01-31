package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;

import java.util.List;
import java.util.Optional;

public interface CharacterRepository {

    Optional<Character> findById(int c_idx);
    Optional<Character> findByName(String c_name);
    List<Character> findAll();
}
