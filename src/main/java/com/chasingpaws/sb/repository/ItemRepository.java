package com.chasingpaws.sb.repository;

import com.chasingpaws.sb.domain.Item;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface ItemRepository {

    Optional<Item> findById(int i_idx);
    List<Item> findAll();
    List<Item> findAllByStage(Map map);
}
