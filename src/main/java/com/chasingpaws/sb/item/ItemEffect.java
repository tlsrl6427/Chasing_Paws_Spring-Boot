package com.chasingpaws.sb.item;

import com.chasingpaws.sb.domain.Item;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemEffect {

	public static List<Item> effect_to_item(List<Item> item_list, int stage_num){
		
		List<Item> selected_item_list = new ArrayList<Item>();
		
		//랜덤으로 아이템 3개 정하기
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		int[] random_item = new int[3];
		for(int i=0; i<3; i++) {
			int random_val = random.nextInt(item_list.size());
			
			for(int k=0; k<i; k++) {
				while(random_item[k]==random_val) {
					random_val = random.nextInt(item_list.size());
				}
			}
			random_item[i] = random_val;
//			System.out.println("랜덤수: " + random_item[i]);
		}
		
		//효과부여하기
		random.setSeed(System.currentTimeMillis());
		for(int i=0; i<3; i++) {
			if(item_list.get(random_item[i]).getI_category().equals("무기")) {//무기일때
				
				if(item_list.get(random_item[i]).getI_level().equals("일반")) {
					
					int ad_increase = random.nextInt(5)+1 + (stage_num-1) * 10;
					int ap_increase = random.nextInt(5)+1 + (stage_num-1) * 10;
					item_list.get(random_item[i]).setI_ad(ad_increase);
					item_list.get(random_item[i]).setI_ap(ap_increase);
					
					
				}else if(item_list.get(random_item[i]).getI_level().equals("고급")) {
					
					int ad_increase = random.nextInt(7)+3 + (stage_num-1) * 15;
					int ap_increase = random.nextInt(7)+3 + (stage_num-1) * 15;
					item_list.get(random_item[i]).setI_ad(ad_increase);
					item_list.get(random_item[i]).setI_ap(ap_increase);
					
					//보조스탯(크리티컬, ad, ap) +
					random.setSeed(System.currentTimeMillis());
					int random_effect  = random.nextInt(3)+1;
					//System.out.println("랜덤수: " + random_effect);
					random.setSeed(System.currentTimeMillis());
					if(random_effect==1) {
						int critical_increase = random.nextInt(5)+1 + (stage_num-1) * 5;
						item_list.get(random_item[i]).setI_critical(critical_increase);
					}else if(random_effect==2) {
						int ad_percent_increase = random.nextInt(5)+1 + (stage_num-1) * 3;
						item_list.get(random_item[i]).setI_ad_percent(ad_percent_increase);
					}else{
						int ap_percent_increase = random.nextInt(5)+1 + (stage_num-1) * 3;
						item_list.get(random_item[i]).setI_ap_percent(ap_percent_increase);
					}
					
				}else if(item_list.get(random_item[i]).getI_level().equals("희귀")) {
					
					int ad_increase = random.nextInt(5)+3 + (stage_num-1) * 15;
					int ap_increase = random.nextInt(5)+3 + (stage_num-1) * 15;
					item_list.get(random_item[i]).setI_ad(ad_increase);
					item_list.get(random_item[i]).setI_ap(ap_increase);
					
					//보조스탯(크리티컬, ad, ap) +
					random.setSeed(System.currentTimeMillis());
					int random_effect  = random.nextInt(3)+1;
					//System.out.println("랜덤수: " + random_effect);
					random.setSeed(System.currentTimeMillis());
					if(random_effect==1) {
						int critical_increase = random.nextInt(5)+1 + (stage_num-1) * 5;
						item_list.get(random_item[i]).setI_critical(critical_increase);
					}else if(random_effect==2) {
						int ad_percent_increase = random.nextInt(5)+1 + (stage_num-1) * 3;
						item_list.get(random_item[i]).setI_ad_percent(ad_percent_increase);
					}else{
						int ap_percent_increase = random.nextInt(5)+1 + (stage_num-1) * 3;
						item_list.get(random_item[i]).setI_ap_percent(ap_percent_increase);
					}
				}
				
			}else {//방어구일때
				if(item_list.get(random_item[i]).getI_level().equals("일반")) {
					//스테이지 1이면 5~10 체력, 방어력
					//스테이지 2이면 11~15
					//스테이지 3이면 16~20
					//체력 방어력 + 
					int hp_increase = random.nextInt(5) + stage_num * 5 + 1;
					int armor_increase = random.nextInt(5) + stage_num * 5 + 1;
					item_list.get(random_item[i]).setI_hp(hp_increase);
					item_list.get(random_item[i]).setI_armor(armor_increase);
					
				}else if(item_list.get(random_item[i]).getI_level().equals("고급")) {
					//스테이지 2이면 11~15 + 회피율 5~7%, 체력·방어력 11~15%
					//스테이지 3이면 16~20 + 회피율 7~10%, 체력·방어력 16~20%
					//체력 방어력 +
					int hp_increase = random.nextInt(5) + stage_num * 5 + 1;
					int armor_increase = random.nextInt(5) + stage_num * 5 + 1;
					item_list.get(random_item[i]).setI_hp(hp_increase);
					item_list.get(random_item[i]).setI_armor(armor_increase);
					
					//보조스탯(회피율, 체력%, 방어력%) +
					random.setSeed(System.currentTimeMillis());
					int random_effect  = random.nextInt(3)+1;
					//System.out.println("랜덤수: " + random_effect);
					random.setSeed(System.currentTimeMillis());
					if(random_effect==1) {
						int avd_increase = random.nextInt(3)+1 + stage_num * 5;
						item_list.get(random_item[i]).setI_avd(avd_increase);
					}else if(random_effect==2) {
						int hp_percent_increase = random.nextInt(5)+1 + stage_num * 5;
						item_list.get(random_item[i]).setI_hp_percent(hp_percent_increase);
					}else{
						int armor_percent_increase = random.nextInt(5)+1 + stage_num * 5;
						item_list.get(random_item[i]).setI_armor_percent(armor_percent_increase);
					}
				}else if(item_list.get(random_item[i]).getI_level().equals("희귀")) {
					//스테이지 1이면 5~10 + 회피율 3~5%, 체력·방어력 5~10% + 특수스킬
					//스테이지 2이면 11~15 + 회피율 5~7%, 체력·방어력 11~15%
					//스테이지 3이면 16~20 + 회피율 7~10%, 체력·방어력 16~20%
					//스테이지 1이면 5~10 + 회피율 3~5%, 체력·방어력 5~10%
					//스테이지 2이면 11~15 + 회피율 5~7%, 체력·방어력 11~15%
					//스테이지 3이면 16~20 + 회피율 7~10%, 체력·방어력 16~20%
					
					//체력 방어력 +
					int hp_increase = random.nextInt(5) + stage_num * 5 + 1;
					int armor_increase = random.nextInt(5) + stage_num * 5 + 1;
					item_list.get(random_item[i]).setI_hp(hp_increase);
					item_list.get(random_item[i]).setI_armor(armor_increase);
					
					//보조스탯(회피율, 체력%, 방어력%) +
					random.setSeed(System.currentTimeMillis());
					int random_effect  = random.nextInt(3);
					random.setSeed(System.currentTimeMillis());
					if(random_effect==1) {
						int avd_increase = random.nextInt(2) + stage_num * 3;
						item_list.get(random_item[i]).setI_avd(avd_increase);
					}else if(random_effect==2) {
						int hp_percent_increase = random.nextInt(4) + stage_num * 5;
						item_list.get(random_item[i]).setI_hp_percent(hp_percent_increase);
					}else{
						int armor_percent_increase = random.nextInt(4) + stage_num * 5;
						item_list.get(random_item[i]).setI_armor_percent(armor_percent_increase);
					}
				}
				
			}// end 방어구일때
			item_list.get(random_item[i]);
		}
		//아이템 확인
		for(int i=0; i<3; i++) {
			Item vo = item_list.get(random_item[i]);
			int cookie = (vo.getI_ad() + vo.getI_ad_percent() + vo.getI_ap() + vo.getI_ap_percent() + vo.getI_critical()
							+vo.getI_armor() + vo.getI_armor_percent() + vo.getI_hp() + vo.getI_hp_percent() + vo.getI_avd()) * 3/2;
			item_list.get(random_item[i]).setI_cookie(cookie);//쿠키적용시키기
			selected_item_list.add(item_list.get(random_item[i]));
//			System.out.println("담아놓은 아이템: " +item_list.get(random_item[i]).getI_name());
//			System.out.println("hp_increase: " +item_list.get(random_item[i]).getI_hp());
//			System.out.println("hp_percent_increase: " +item_list.get(random_item[i]).getI_hp_percent());
//			System.out.println("ad_increase: " +item_list.get(random_item[i]).getI_ad());
//			System.out.println("ad_percent_increase: " +item_list.get(random_item[i]).getI_ad_percent());
//			System.out.println("ap_increase: " +item_list.get(random_item[i]).getI_ap());
//			System.out.println("ap_percent_increase: " +item_list.get(random_item[i]).getI_ap_percent());
//			System.out.println("armor_increase: " +item_list.get(random_item[i]).getI_armor());
//			System.out.println("armor_percent_increase: " +item_list.get(random_item[i]).getI_armor_percent());
//			System.out.println("critical_increase: " +item_list.get(random_item[i]).getI_critical());
//			System.out.println("avd_increase: " +item_list.get(random_item[i]).getI_avd());
		}
		
		return selected_item_list;
	}
}
