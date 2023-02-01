package com.chasingpaws.sb.service;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Skill;
import com.chasingpaws.sb.repository.CharacterRepository;
import com.chasingpaws.sb.repository.SkillRepository;
import org.checkerframework.checker.units.qual.C;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final SkillRepository skillRepository;

    public CharacterService(CharacterRepository characterRepository, SkillRepository skillRepository) {
        this.characterRepository = characterRepository;
        this.skillRepository = skillRepository;
    }

    public Character findOne(int c_idx){
        Character character = characterRepository.findById(c_idx).get();
        List<Skill> skill = skillRepository.findAllById(c_idx);
        character.setSkill(skill);
        return character;
    }
}
