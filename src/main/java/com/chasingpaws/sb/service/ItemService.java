package com.chasingpaws.sb.service;

import com.chasingpaws.sb.domain.Item;
import com.chasingpaws.sb.repository.ItemRepository;

import java.util.List;
import java.util.Map;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> findItems(Map map){
        return itemRepository.findAllByStage(map);
    }
}
