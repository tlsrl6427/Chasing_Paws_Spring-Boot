package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Character;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import java.util.Optional;

@SpringBootTest
@Transactional
public class JpaCharacterRepositoryTest {

    @Autowired
    JpaCharacterRepository jpaCharacterRepository;

    //EntityManager em;

   /* @BeforeEach
    public void beforeEach(){
        jpaCharacterRepository = new JpaCharacterRepository(em);
    }*/

    @Test
    public void findById(){
        //given
        Character character = new Character();
        character.setC_idx(1);
        character.setC_name("전사");
        character.setC_hp(120);
        character.setC_ad(10);
        character.setC_ap(0);
        character.setC_armor(0);
        character.setC_critical(0);
        character.setC_avd(5);
        character.setC_auto_attack("베기");
        character.setC_p_skill("퓨리");
        character.setC_img("warrior.png");
        character.setC_auto_attack_img("img/skill/warrior/1베기.png");
        character.setC_p_skill_img("img/skill/warrior/전사패시브.png");

        //when
        Character result = jpaCharacterRepository.findById(1).get();


        //then
        Assertions.assertThat(character.getC_name()).isEqualTo(result.getC_name());
    }

    @Test
    public void findByName(){
        //given
        Character character = new Character();
        character.setC_idx(1);
        character.setC_name("전사");
        character.setC_hp(120);
        character.setC_ad(10);
        character.setC_ap(0);
        character.setC_armor(0);
        character.setC_critical(0);
        character.setC_avd(5);
        character.setC_auto_attack("베기");
        character.setC_p_skill("퓨리");
        character.setC_img("warrior.png");
        character.setC_auto_attack_img("img/skill/warrior/1베기.png");
        character.setC_p_skill_img("img/skill/warrior/전사패시브.png");

        //when
        //Character result = jpaCharacterRepository.findById(2).get();
        if(jpaCharacterRepository.findByName("전사").isEmpty()) System.out.println("empty");
        else System.out.println("present");

        //then
        //Assertions.assertThat(character).isEqualTo(result);
    }

    @Test
    public void findAll(){

    }
}
