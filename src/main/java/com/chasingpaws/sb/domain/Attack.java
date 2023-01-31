package com.chasingpaws.sb.domain;

public class Attack {

    String name;
    int damage;
    int self_damage;
    int damage_reduced;
    int damage_reduced_turn;
    int ad_increased;
    int armor_increased;
    int ad_reduced;
    int armor_reduced;
    int dot_damage;
    int dot_damage_turn;
    int cc_turn;
    String cc_name;
    String battle_info;
    String battle_info_p;

    public String getBattle_info() {
        return battle_info;
    }
    public void setBattle_info(String battle_info) {
        this.battle_info = battle_info;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getDamage_reduced_turn() {
        return damage_reduced_turn;
    }
    public void setDamage_reduced_turn(int damage_reduced_turn) {
        this.damage_reduced_turn = damage_reduced_turn;
    }
    public int getSelf_damage() {
        return self_damage;
    }
    public void setSelf_damage(int self_damage) {
        this.self_damage = self_damage;
    }
    public int getDamage() {
        return damage;
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getDamage_reduced() {
        return damage_reduced;
    }
    public void setDamage_reduced(int damage_reduced) {
        this.damage_reduced = damage_reduced;
    }
    public int getAd_increased() {
        return ad_increased;
    }
    public void setAd_increased(int ad_increased) {
        this.ad_increased = ad_increased;
    }
    public int getArmor_increased() {
        return armor_increased;
    }
    public void setArmor_increased(int armor_increased) {
        this.armor_increased = armor_increased;
    }
    public int getAd_reduced() {
        return ad_reduced;
    }
    public void setAd_reduced(int ad_reduced) {
        this.ad_reduced = ad_reduced;
    }
    public int getArmor_reduced() {
        return armor_reduced;
    }
    public void setArmor_reduced(int armor_reduced) {
        this.armor_reduced = armor_reduced;
    }
    public int getDot_damage() {
        return dot_damage;
    }
    public void setDot_damage(int dot_damage) {
        this.dot_damage = dot_damage;
    }
    public int getDot_damage_turn() {
        return dot_damage_turn;
    }
    public void setDot_damage_turn(int dot_damage_turn) {
        this.dot_damage_turn = dot_damage_turn;
    }
    public int getCc_turn() {
        return cc_turn;
    }
    public void setCc_turn(int cc_turn) {
        this.cc_turn = cc_turn;
    }
    public String getCc_name() {
        return cc_name;
    }
    public void setCc_name(String cc_name) {
        this.cc_name = cc_name;
    }
    public String getBattle_info_p() {
        return battle_info_p;
    }
    public void setBattle_info_p(String battle_info_p) {
        this.battle_info_p = battle_info_p;
    }
}
