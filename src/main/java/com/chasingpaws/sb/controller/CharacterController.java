package com.chasingpaws.sb.controller;

import com.chasingpaws.sb.info.character.Archer;
import com.chasingpaws.sb.info.character.Warrior;
import com.chasingpaws.sb.info.character.Wizard;
import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

@Controller
@RequestMapping("/character")
public class CharacterController {

    private final CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @Autowired
    ServletContext application;

    @GetMapping("/choice_form")
    public String choice_form(){
        return "character/choice";
    }

    @GetMapping("/choice")
    public String character_choice(int c_idx, Model model) {
        //캐릭터 선택에서 넘어왔으면 받은 캐릭터 정보 토대로 pageScope에 추가
        Character ex_c_vo = characterService.findOne(c_idx).get();
        Character main_ch = null;

        if(c_idx==1) {//전사
            main_ch = new Warrior();
            main_ch.setC_idx(ex_c_vo.getC_idx());
            main_ch.setC_name(ex_c_vo.getC_name());
            main_ch.setC_hp(ex_c_vo.getC_hp());
            main_ch.setC_ad(ex_c_vo.getC_ad());
            main_ch.setC_ap(ex_c_vo.getC_ap());
            main_ch.setC_armor(ex_c_vo.getC_armor());
            main_ch.setC_critical(ex_c_vo.getC_critical());
            main_ch.setC_avd(ex_c_vo.getC_avd());
            main_ch.setC_auto_attack(ex_c_vo.getC_auto_attack());
            main_ch.setC_p_skill(ex_c_vo.getC_p_skill());
            main_ch.setC_img(ex_c_vo.getC_img());
            main_ch.setSkill(ex_c_vo.getSkill());
            main_ch.setC_original_hp(ex_c_vo.getC_hp());
            main_ch.setC_auto_attack_img(ex_c_vo.getC_auto_attack_img());
            main_ch.setC_p_skill_img(ex_c_vo.getC_p_skill_img());
        }else if(c_idx==2) {//궁수
            main_ch = new Archer();
            main_ch.setC_idx(ex_c_vo.getC_idx());
            main_ch.setC_name(ex_c_vo.getC_name());
            main_ch.setC_hp(ex_c_vo.getC_hp());
            main_ch.setC_ad(ex_c_vo.getC_ad());
            main_ch.setC_ap(ex_c_vo.getC_ap());
            main_ch.setC_armor(ex_c_vo.getC_armor());
            main_ch.setC_critical(ex_c_vo.getC_critical());
            main_ch.setC_avd(ex_c_vo.getC_avd());
            main_ch.setC_auto_attack(ex_c_vo.getC_auto_attack());
            main_ch.setC_p_skill(ex_c_vo.getC_p_skill());
            main_ch.setC_img(ex_c_vo.getC_img());
            main_ch.setSkill(ex_c_vo.getSkill());
            main_ch.setC_original_hp(ex_c_vo.getC_hp());
            main_ch.setC_auto_attack_img(ex_c_vo.getC_auto_attack_img());
            main_ch.setC_p_skill_img(ex_c_vo.getC_p_skill_img());
        }else{//법사
            main_ch = new Wizard();
            main_ch.setC_idx(ex_c_vo.getC_idx());
            main_ch.setC_name(ex_c_vo.getC_name());
            main_ch.setC_hp(ex_c_vo.getC_hp());
            main_ch.setC_ad(ex_c_vo.getC_ad());
            main_ch.setC_ap(ex_c_vo.getC_ap());
            main_ch.setC_armor(ex_c_vo.getC_armor());
            main_ch.setC_critical(ex_c_vo.getC_critical());
            main_ch.setC_avd(ex_c_vo.getC_avd());
            main_ch.setC_auto_attack(ex_c_vo.getC_auto_attack());
            main_ch.setC_p_skill(ex_c_vo.getC_p_skill());
            main_ch.setC_img(ex_c_vo.getC_img());
            main_ch.setSkill(ex_c_vo.getSkill());
            main_ch.setC_original_hp(ex_c_vo.getC_hp());
            main_ch.setC_auto_attack_img(ex_c_vo.getC_auto_attack_img());
            main_ch.setC_p_skill_img(ex_c_vo.getC_p_skill_img());
        }


        application.setAttribute("main_ch", main_ch);
        //model.addAttribute("stage_val", 1);

        return "redirect:/shop/show?stage_val=1";
    }
}
