package com.coffee.pos.service;

import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.model.Goods;
import com.coffee.pos.repository.GoodsRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class GoodsService {

    @Autowired private GoodsRepository goodsRepository;

    public Page<Goods> getAll(Pageable pageable) {
        return goodsRepository.findAll(pageable);
    }

    public Goods save(CreateGoodsDTO goods) {
        Goods newGoods = mapToEntity(goods);
        return goodsRepository.save(newGoods);
    }

    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    public Goods findById(String id) {
        return goodsRepository.findById(id).orElse(null);
    }

    public Page<Goods> findByName(String name, Pageable pageable) {
        return goodsRepository.findByNameContaining(name, pageable);
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
