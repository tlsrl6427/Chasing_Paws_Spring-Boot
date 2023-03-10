package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Skill;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

public interface SkillRepository {

    Optional<Skill> findById(int s_idx);
    Optional<Skill> findByName(String s_name);
    List<Skill> findAllById(int c_idx);
}
