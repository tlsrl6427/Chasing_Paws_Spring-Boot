package com.chasingpaws.sb;

import com.chasingpaws.sb.repository.CharacterRepository;
import com.chasingpaws.sb.repository.ItemRepository;
import com.chasingpaws.sb.repository.JpaCharacterRepository;
import com.chasingpaws.sb.repository.JpaItemRepository;
import com.chasingpaws.sb.service.CharacterService;
import com.chasingpaws.sb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
public class SpringConfig {

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public CharacterService characterService(){
        return new CharacterService(characterRepository());
    }

    @Bean
    public ItemService itemService(){ return new ItemService(itemRepository());}

    @Bean
    public CharacterRepository characterRepository(){
        return new JpaCharacterRepository(em);
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepository(em);
    }
}
