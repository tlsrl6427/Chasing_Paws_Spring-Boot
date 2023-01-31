package com.chasingpaws.sb.domain;

import javax.persistence.Id;

public class Skill {

    @Id
    int s_idx;
    String s_name;
    int s_num;
    String s_category;
    int s_turn;
    int s_valid;
    int c_idx;
    int s_basic_damage;
    int s_add_damage;
    int s_coeff_ad;
    int s_coeff_ap;
    String s_info;
    String s_img;


    public String getS_img() {
        return s_img;
    }
    public void setS_img(String s_img) {
        this.s_img = s_img;
    }
    public String getS_info() {
        return s_info;
    }
    public void setS_info(String s_info) {
        this.s_info = s_info;
    }
    public int getS_coeff_ad() {
        return s_coeff_ad;
    }
    public void setS_coeff_ad(int s_coeff_ad) {
        this.s_coeff_ad = s_coeff_ad;
    }
    public int getS_coeff_ap() {
        return s_coeff_ap;
    }
    public void setS_coeff_ap(int s_coeff_ap) {
        this.s_coeff_ap = s_coeff_ap;
    }
    public int getS_basic_damage() {
        return s_basic_damage;
    }
    public void setS_basic_damage(int s_basic_damage) {
        this.s_basic_damage = s_basic_damage;
    }
    public int getS_add_damage() {
        return s_add_damage;
    }
    public void setS_add_damage(int s_add_damage) {
        this.s_add_damage = s_add_damage;
    }
    public String getS_name() {
        return s_name;
    }
    public void setS_name(String s_name) {
        this.s_name = s_name;
    }
    public int getS_num() {
        return s_num;
    }
    public void setS_num(int s_num) {
        this.s_num = s_num;
    }
    public int getS_valid() {
        return s_valid;
    }
    public void setS_valid(int s_valid) {
        this.s_valid = s_valid;
    }
    public int getS_idx() {
        return s_idx;
    }
    public void setS_idx(int s_idx) {
        this.s_idx = s_idx;
    }
    public String getS_category() {
        return s_category;
    }
    public void setS_category(String s_category) {
        this.s_category = s_category;
    }
    public int getS_turn() {
        return s_turn;
    }
    public void setS_turn(int s_turn) {
        this.s_turn = s_turn;
    }
    public int getC_idx() {
        return c_idx;
    }
    public void setC_idx(int c_idx) {
        this.c_idx = c_idx;
    }

}
