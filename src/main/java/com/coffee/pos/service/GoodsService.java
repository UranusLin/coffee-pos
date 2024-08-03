package com.coffee.pos.service;

import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.model.Goods;
import com.coffee.pos.repository.GoodsRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired private GoodsRepository goodsRepository;

    public List<Goods> getAll() {
        return goodsRepository.findAll();
    }

    public Goods save(CreateGoodsDTO goods) {
        Goods newGoods = mapToEntity(goods);
        return goodsRepository.save(newGoods);
    }

    public Goods findById(String id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public Goods findByName(String name) {
        return goodsRepository.findByName(name);
    }

    private Goods mapToEntity(CreateGoodsDTO goods) {
        Goods newGoods = new Goods();
        newGoods.setName(goods.getName());
        newGoods.setDescription(goods.getDescription());
        newGoods.setAmount(goods.getAmount());
        newGoods.setCreateAt(LocalDateTime.now());
        newGoods.setUpdateAt(LocalDateTime.now());
        return newGoods;
    }
}
