package com.chasingpaws.sb.info.character;

import com.chasingpaws.sb.domain.Attack;
import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Mop;

import java.util.Random;

public class Wizard extends Character {
   
   Random random = new Random();
   
   public void active_skill1(Mop mop, Attack attack_mop) {
      // 아이스볼 10(1.5ap)
      System.out.println("아이스볼!");
      int damage = getSkill().get(0).getS_basic_damage() +
				getSkill().get(0).getS_add_damage() * getActive_skill_level()[0] +
				getSkill().get(0).getS_coeff_ad() * getC_ad() +
				getSkill().get(0).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage * (5000 / (50 + mop.getM_armor())) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / (50 + mop.getM_armor())) / 100);
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", this.getC_name(),
            mop.getM_name(), attack_mop.getName(), damage * (5000 / (50 + mop.getM_armor())) / 100));

      // ***********패시브***********
      // 매지컬 탤런트 (데미지를 입힐때 확률적으로 스킬의 턴수가 한턴 줄어듭니다)
      
      if (random.nextInt(100) <= 20) {
         System.out.println("패시브 스킬발동");
         Character.setWizard_passive(true);
         attack_mop.setBattle_info_p(String.format("패시브 스킬 발동!\n모든 스킬의 쿨타임이 한 턴씩 줄어듭니다."));
      }

   }

   public void active_skill2(Mop mop, Attack attack_mop) {
      // 썬더볼트 30(2ap)
      System.out.println("썬더볼트!");
      int damage = getSkill().get(1).getS_basic_damage() +
				getSkill().get(1).getS_add_damage() * getActive_skill_level()[1] +
				getSkill().get(1).getS_coeff_ad() * getC_ad() +
				getSkill().get(1).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage * (5000 / (50 + mop.getM_armor())) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / (50 + mop.getM_armor())) / 100);
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", this.getC_name(),
            mop.getM_name(), attack_mop.getName(), damage * (5000 / (50 + mop.getM_armor())) / 100));

      // ***********패시브***********
      // 매지컬 탤런트 (데미지를 입힐때 확률적으로 스킬의 턴수가 한턴 줄어듭니다)
      if (random.nextInt(100) <= 20) {
         System.out.println("패시브 스킬발동");
         Character.setWizard_passive(true);
         attack_mop.setBattle_info_p(String.format("패시브 스킬 발동!\n모든 스킬의 쿨타임이 한 턴씩 줄어듭니다."));
      }
   }

   public void active_skill3(Mop mop, Attack attack_mop) {
      // 메테오 50(2.5ap)
      System.out.println("메테오!");
      int damage = getSkill().get(2).getS_basic_damage() +
				getSkill().get(2).getS_add_damage() * getActive_skill_level()[2] +
				getSkill().get(2).getS_coeff_ad() * getC_ad() +
				getSkill().get(2).getS_coeff_ap() * getC_ap();
      attack_mop.setDamage(damage * (5000 / (50 + mop.getM_armor())) / 100);
      mop.setM_hp(mop.getM_hp() - damage * (5000 / (50 + mop.getM_armor())) / 100);
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", this.getC_name(),
            mop.getM_name(), attack_mop.getName(), damage * (5000 / (50 + mop.getM_armor())) / 100));

      // ***********패시브***********
      // 매지컬 탤런트 (데미지를 입힐때 확률적으로 스킬의 턴수가 한턴 줄어듭니다)
      if (random.nextInt(100) <= 20) {
         System.out.println("패시브 스킬발동");
         Character.setWizard_passive(true);
         attack_mop.setBattle_info_p(String.format("패시브 스킬 발동!\n모든 스킬의 쿨타임이 한 턴씩 줄어듭니다."));
      }
   }

   public void active_skill4(Mop mop, Attack attack_mop) {
      // 리퍼(전체 체력 비례 데미지, 일단 25%로 고정)
      System.out.println("리퍼!");
      // 추후 구현 예정
      attack_mop.setBattle_info(String.format("%s(이)가 %s에게 %s(으)로 %d의 피해를 입혔습니다.", this.getC_name(),
            mop.getM_name(), attack_mop.getName(), mop.getM_hp() - mop.getM_hp() * (1 / 4)));

      // ***********패시브***********
      // 매지컬 탤런트 (데미지를 입힐때 확률적으로 스킬의 턴수가 한턴 줄어듭니다)
      if (random.nextInt(100) <= 20) {
         System.out.println("패시브 스킬발동");
         Character.setWizard_passive(true);
         attack_mop.setBattle_info_p(String.format("패시브 스킬 발동!\n모든 스킬의 쿨타임이 한 턴씩 줄어듭니다."));
      }
   }

   public void active_skill5(Mop mop, Attack attack_mop) {
      // 쉐도우실드(쉴드 생성, 10 + 2ap)
      System.out.println("리퍼!");
      int shiled = 10 + 2 * this.getC_ap();
      this.setC_hp(shiled);
   }

   public void active_skill6(Mop mop, Attack attack_mop) {
      // 인비저블(쉴드 생성, 10 + 2ap)
      System.out.println("인비저블!");
      int damage_reduced = getSkill().get(5).getS_basic_damage() +
				getSkill().get(5).getS_add_damage() * getActive_skill_level()[5] +
				getSkill().get(5).getS_coeff_ad() * getC_ad() +
				getSkill().get(5).getS_coeff_ap() * getC_ap();
      int damage_reduced_turn = getSkill().get(5).getS_turn();
      this.setDamage_reduced(getDamage_reduced() + damage_reduced);
      this.setDamage_reduced_turn(damage_reduced);
      attack_mop.setDamage_reduced(damage_reduced_turn);
      attack_mop.setDamage_reduced_turn(damage_reduced_turn);
      // return 6;
      attack_mop.setBattle_info(String.format("%s(이)가  %s(으)로 %d턴동안 %d의 실드를 생성합니다.", this.getC_name(),
            attack_mop.getName(), damage_reduced_turn, damage_reduced));

   }

   public void active_skill7(Mop mop, Attack attack_mop) {
      // 프로즌(2턴 동안 적을 얼림)
      System.out.println("프로즌!");
      attack_mop.setCc_turn(3);
      mop.setCc_turn(3);
      attack_mop.setBattle_info(String.format("%s(이)가  차가운 바람으로 몬스터를 2턴동안 얼립니다.", this.getC_name()));
   }

   public void active_skill8(Mop mop, Attack attack_mop) {
      // 큐어링(10 + 2 * ap)
      System.out.println("큐어링!");
      this.setC_hp(getC_hp() + 30 + 2 * getC_ap());
      attack_mop.setBattle_info(
            String.format("%s(이)가 자신의 hp를 %d만큼 회복하였습니다.", this.getC_name(), getC_hp() + 10 + 2 * getC_ap()));
   }

}