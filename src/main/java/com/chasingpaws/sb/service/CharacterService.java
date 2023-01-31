package com.chasingpaws.sb.service;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.repository.CharacterRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
public class CharacterService {

    private final CharacterRepository characterRepository;

    public CharacterService(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public Optional<Character> findOne(int c_idx){
        return characterRepository.findById(c_idx);
    }
}
