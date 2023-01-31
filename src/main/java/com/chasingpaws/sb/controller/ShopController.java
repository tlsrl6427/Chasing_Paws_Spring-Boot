package com.chasingpaws.sb.controller;

import com.chasingpaws.sb.domain.Character;
import com.chasingpaws.sb.domain.Item;
import com.chasingpaws.sb.item.ItemEffect;
import com.chasingpaws.sb.repository.ItemRepository;
import com.chasingpaws.sb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    ServletContext application;

    private final ItemService itemService;

    public ShopController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping("/show")
    public String shop(Model model, int stage_val) {

        Character main_ch = (Character) application.getAttribute("main_ch");
        model.addAttribute("main_ch", main_ch);

        List<Item> item_list = new ArrayList<Item>();
        List<Item> selected_item_list = null;

        int stage_num = (stage_val-1) / 4 + 1;

        Map map_pub = new HashMap();
        Map map_ch = new HashMap();
        map_ch.put("i_class", main_ch.getC_name());
        map_pub.put("i_class", "공용");
        if(stage_num==1) {//1 스테이지 일때
            map_pub.put("i_level", "일반");
            map_ch.put("i_level", "일반");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            selected_item_list = ItemEffect.effect_to_item(item_list, stage_num);
        }else if(stage_num==2) {//2 스테이지 일때
            map_pub.put("i_level", "일반");
            map_ch.put("i_level", "일반");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            map_pub.put("i_level", "고급");
            map_ch.put("i_level", "고급");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            selected_item_list = ItemEffect.effect_to_item(item_list, stage_num);
        }else if(stage_num==3) {//3 스테이지 일때
            map_pub.put("i_level", "일반");
            map_ch.put("i_level", "일반");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            map_pub.put("i_level", "고급");
            map_ch.put("i_level", "고급");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            map_pub.put("i_level", "희귀");
            map_ch.put("i_level", "희귀");
            item_list.addAll(itemService.findItems(map_pub));
            item_list.addAll(itemService.findItems(map_ch));
            selected_item_list = ItemEffect.effect_to_item(item_list, stage_num);
        }



//      for(ItemVo vo : selected_item_list) {
//         System.out.println(vo.getI_name());
//      }

        application.setAttribute("selected_item_list", selected_item_list);
        model.addAttribute("selected_item_list", selected_item_list);
        return "game/shop/shop";
    }
}
