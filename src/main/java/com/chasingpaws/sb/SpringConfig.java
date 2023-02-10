package com.chasingpaws.sb;

import com.chasingpaws.sb.repository.*;
import com.chasingpaws.sb.service.CharacterService;
import com.chasingpaws.sb.service.ItemService;
import com.chasingpaws.sb.service.MopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;
    private final EntityManager em;

    @Autowired
    public SpringConfig(DataSource dataSource, EntityManager em) {
        this.dataSource = dataSource;
        this.em = em;
    }

    @Bean
    public CharacterService characterService(){
        return new CharacterService(characterRepository(), skillRepository());
    }

    @Bean
    public ItemService itemService(){ return new ItemService(itemRepository());}

    @Bean
    public MopService mopService(){return new MopService(mopRepository());}

    @Bean
    public CharacterRepository characterRepository(){
        return new JpaCharacterRepository(em);
    }

    @Bean
    public ItemRepository itemRepository(){
        return new JpaItemRepository(em);
    }

    @Bean
    public SkillRepository skillRepository(){ return new JpaSkillRepository(em);}

    @Bean
    public MopRepository mopRepository(){return new JpaMopRepository(em);}
}
