package com.chasingpaws.sb.info.character;

import com.chasingpaws.sb.domain.Attack;
import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Mop;

public class Warrior extends Character {

   boolean flag = true;
   
   public void active_skill1(Mop mop, Attack attack_mop) {
      //어스 브레이크 10(1.5ad)
      System.out.println("어스 브레이크!");
      int damage = getSkill().get(0).getS_basic_damage() +
    		  				getSkill().get(0).getS_add_damage() * getActive_skill_level()[0] +
    		  				getSkill().get(0).getS_coeff_ad() * getC_ad() +
    		  				getSkill().get(0).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100 );
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.",
                                                         this.getC_name(), 
                                                         mop.getM_name(),
                                                         attack_mop.getName(),
                                                         damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100));
   }
   
   public void active_skill2(Mop mop, Attack attack_mop) {
      //러닝 크래쉬 80입히고/20받기
      System.out.println("러닝 크래쉬!");
      int damage = getSkill().get(1).getS_basic_damage() +
				getSkill().get(1).getS_add_damage() * getActive_skill_level()[1] +
				getSkill().get(1).getS_coeff_ad() * getC_ad() +
				getSkill().get(1).getS_coeff_ap() * getC_ap();
      int self_damage = damage/4;
      attack_mop.setSelf_damage(self_damage * (5000 / ( 50 + this.getC_armor() ) ) / 100);
      attack_mop.setDamage(damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100);
      this.setC_hp(this.getC_hp()-self_damage *  (5000 / ( 50 + this.getC_armor() ) ) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100);
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.\n%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.",
                                                      this.getC_name(), 
                                                      mop.getM_name(),
                                                      attack_mop.getName(),
                                                      damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100,
                                                      this.getC_name(), 
                                                      this.getC_name(), 
                                                      attack_mop.getName(),
                                                      self_damage * (5000 / ( 50 + this.getC_armor() ) ) / 100));
   }
   
   public void active_skill3(Mop mop, Attack attack_mop) {
      //소울 슬래쉬 50(1.0ad)(0.6ap)
      System.out.println("소울 슬래쉬!");
      int damage = getSkill().get(2).getS_basic_damage() +
				getSkill().get(2).getS_add_damage() * getActive_skill_level()[2] +
				getSkill().get(2).getS_coeff_ad() * getC_ad() +
				getSkill().get(2).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100 );
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.",
                                                         this.getC_name(), 
                                                         mop.getM_name(),
                                                         attack_mop.getName(),
                                                         damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100));
   }
   
   public void active_skill4(Mop mop, Attack attack_mop) {
      //심판의 검 (잃은 체력 or 총 체력 비례 ?)
      int damage = getSkill().get(3).getS_basic_damage() +
				getSkill().get(3).getS_add_damage() * getActive_skill_level()[3] +
				getSkill().get(3).getS_coeff_ad() * getC_ad() +
				getSkill().get(3).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage);
      mop.setM_hp(mop.getM_hp() - damage );
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.",
                                                      this.getC_name(), 
                                                      mop.getM_name(),
                                                      attack_mop.getName(),
                                                      damage * (5000 / ( 50 + mop.getM_armor() ) ) / 100));
   }
   
   public void active_skill5(Mop mop, Attack attack_mop) {
      //아테나의 축복 5/5 증가
      System.out.println("--아테나의 축복--!");
      int ad_increased = getSkill().get(4).getS_basic_damage() +
				getSkill().get(4).getS_add_damage() * getActive_skill_level()[4] +
				getSkill().get(4).getS_coeff_ad() * getC_ad() +
				getSkill().get(4).getS_coeff_ap() * getC_ap();
      int armor_increased = getSkill().get(4).getS_basic_damage() +
				getSkill().get(4).getS_add_damage() * getActive_skill_level()[4] +
				getSkill().get(4).getS_coeff_ad() * getC_ad() +
				getSkill().get(4).getS_coeff_ap() * getC_ap();
      this.setC_ad(this.getC_ad() + ad_increased);
      this.setC_armor(this.getC_armor() +  armor_increased);
      attack_mop.setAd_increased(ad_increased);
      attack_mop.setArmor_increased(armor_increased);
      attack_mop.setBattle_info(String.format("%s(이)가 %s(으)로 공격력을 %d 증가시켰습니다.\n%s(이)가 %s(으)로 방어력을  %d 증가시켰습니다.",
                                                         this.getC_name(), 
                                                         attack_mop.getName(),
                                                         ad_increased,
                                                         this.getC_name(), 
                                                         attack_mop.getName(),
                                                         armor_increased));
      //return 5;
   }
   
   public void active_skill6(Mop mop, Attack attack_mop) {
      //아이언 바디(받는 피해 감소)
      System.out.println("아이언 바디!");
      int damage_reduced = getSkill().get(5).getS_basic_damage() +
				getSkill().get(5).getS_add_damage() * getActive_skill_level()[5] +
				getSkill().get(5).getS_coeff_ad() * getC_ad() +
				getSkill().get(5).getS_coeff_ap() * getC_ap();
      int damage_reduced_turn = getSkill().get(5).getS_turn();
      this.setDamage_reduced(getDamage_reduced()+damage_reduced);
      this.setDamage_reduced_turn(damage_reduced);
      attack_mop.setDamage_reduced(damage_reduced_turn);
      attack_mop.setDamage_reduced_turn(damage_reduced_turn);
      //return 6;
      attack_mop.setBattle_info(String.format("%s(이)가  %s(으)로 %d턴동안 %d의 데미지를 감소시킵니다.",
                                                      this.getC_name(), 
                                                      attack_mop.getName(),
                                                      damage_reduced_turn,
                                                      damage_reduced));
   }
   
   public void active_skill7(Mop mop, Attack attack_mop) {
      //오러블레이드(출혈)
      System.out.println("오러블레이드!");
      int dot_damage = getSkill().get(6).getS_basic_damage() +
				getSkill().get(6).getS_add_damage() * getActive_skill_level()[6] +
				getSkill().get(6).getS_coeff_ad() * getC_ad() +
				getSkill().get(6).getS_coeff_ap() * getC_ap();
      int dot_damage_turn = getSkill().get(6).getS_turn();
      attack_mop.setDot_damage(dot_damage);
      attack_mop.setDot_damage_turn(dot_damage_turn);
      mop.setDot_damage(dot_damage);
      mop.setDot_damage_turn(dot_damage_turn);
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %d턴동안 %d의 피해를 입힙니다.",
                                                      this.getC_name(), 
                                                      mop.getM_name(),
                                                      dot_damage_turn,
                                                      dot_damage));
   }
   
   public void active_skill8(Mop mop, Attack attack_mop) {
      //사자후(상대 공격력, 방어력 감소)
      System.out.println("사자후!");
      int ad_reduced = getSkill().get(7).getS_basic_damage() +
				getSkill().get(7).getS_add_damage() * getActive_skill_level()[7] +
				getSkill().get(7).getS_coeff_ad() * getC_ad() +
				getSkill().get(7).getS_coeff_ap() * getC_ap();
      int armor_reduced = getSkill().get(6).getS_basic_damage() +
				getSkill().get(7).getS_add_damage() * getActive_skill_level()[7] +
				getSkill().get(7).getS_coeff_ad() * getC_ad() +
				getSkill().get(7).getS_coeff_ap() * getC_ap();
      attack_mop.setAd_reduced(ad_reduced);
      attack_mop.setArmor_reduced(armor_reduced);
      mop.setM_ad(mop.getM_ad() - ad_reduced);
      mop.setM_armor(mop.getM_armor() - armor_reduced);
      attack_mop.setBattle_info(String.format("%s(이)가 %s(으)로 %s의 공격력을 %d 감소시켰습니다.\n%s(이)가 %s(으)로 %s의 방어력을 %d 감소시켰습니다.",
                                                      this.getC_name(), 
                                                      attack_mop.getName(),
                                                      mop.getM_name(),
                                                      ad_reduced,
                                                      this.getC_name(), 
                                                      attack_mop.getName(),
                                                      mop.getM_name(),
                                                      armor_reduced));
   }
   
   public void passive_skill1(int original_hp) {
      // 피가 30%이하로 떨어지면 방어력이 1.5배 증가합니다.
      
      // 30% 이하로 떨어지면 매번 방어력이 1.5배 증가하는 것이 아닌, 스테이지당 딱 한 번만 기본 방어에 1.5배 수치 적용
      if (((int) (((double)this.getC_hp()) / (double)original_hp * 100) < 30) && flag) {
         this.setC_armor((int)(this.getC_armor() * 1.5));
         flag = false;
      }
   }
}