package com.chasingpaws.sb.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Item {

    @Id
    int 	i_idx;
    String	i_class;
    String 	i_category;
    String 	i_name;
    String 	i_level;
    String 	i_img;

    @Transient
    int 	i_hp;
    @Transient
    int	i_hp_percent;
    @Transient
    int 	i_ad;
    @Transient
    int 	i_ad_percent;
    @Transient
    int 	i_ap;
    @Transient
    int 	i_ap_percent;
    @Transient
    int 	i_armor;
    @Transient
    int 	i_armor_percent;
    @Transient
    int 	i_critical;
    @Transient
    int 	i_avd;

    @Transient
    int	i_effect;
    @Transient
    int 	i_cookie;
	/*
	 	i_effect
	 	1. 피해감소
	 	2. 피격시 데미지 입히기
	 	3. 체력줄어드는 대신 데미지 늘어나기
	 	4. 턴마다 공격력 늘어남
	 	5. 턴 시작시 방어막 생성
	 	6. 체력 1로 부활

	 */



    public int getI_idx() {
        return i_idx;
    }
    public int getI_cookie() {
        return i_cookie;
    }
    public void setI_cookie(int i_cookie) {
        this.i_cookie = i_cookie;
    }
    public String getI_img() {
        return i_img;
    }
    public void setI_img(String i_img) {
        this.i_img = i_img;
    }
    public int getI_hp_percent() {
        return i_hp_percent;
    }
    public void setI_hp_percent(int i_hp_percent) {
        this.i_hp_percent = i_hp_percent;
    }
    public int getI_ad_percent() {
        return i_ad_percent;
    }
    public void setI_ad_percent(int i_ad_percent) {
        this.i_ad_percent = i_ad_percent;
    }
    public int getI_ap_percent() {
        return i_ap_percent;
    }
    public void setI_ap_percent(int i_ap_percent) {
        this.i_ap_percent = i_ap_percent;
    }
    public int getI_armor_percent() {
        return i_armor_percent;
    }
    public void setI_armor_percent(int i_armor_percent) {
        this.i_armor_percent = i_armor_percent;
    }
    public int getI_hp() {
        return i_hp;
    }
    public void setI_hp(int i_hp) {
        this.i_hp = i_hp;
    }
    public int getI_ad() {
        return i_ad;
    }
    public void setI_ad(int i_ad) {
        this.i_ad = i_ad;
    }
    public int getI_ap() {
        return i_ap;
    }
    public void setI_ap(int i_ap) {
        this.i_ap = i_ap;
    }
    public int getI_armor() {
        return i_armor;
    }
    public void setI_armor(int i_armor) {
        this.i_armor = i_armor;
    }
    public int getI_critical() {
        return i_critical;
    }
    public void setI_critical(int i_critical) {
        this.i_critical = i_critical;
    }
    public int getI_avd() {
        return i_avd;
    }
    public void setI_avd(int i_avd) {
        this.i_avd = i_avd;
    }
    public int getI_effect() {
        return i_effect;
    }
    public void setI_effect(int i_effect) {
        this.i_effect = i_effect;
    }
    public void setI_idx(int i_idx) {
        this.i_idx = i_idx;
    }
    public String getI_class() {
        return i_class;
    }
    public void setI_class(String i_class) {
        this.i_class = i_class;
    }
    public String getI_category() {
        return i_category;
    }
    public void setI_category(String i_category) {
        this.i_category = i_category;
    }
    public String getI_name() {
        return i_name;
    }
    public void setI_name(String i_name) {
        this.i_name = i_name;
    }
    public String getI_level() {
        return i_level;
    }
    public void setI_level(String i_level) {
        this.i_level = i_level;
    }
}
