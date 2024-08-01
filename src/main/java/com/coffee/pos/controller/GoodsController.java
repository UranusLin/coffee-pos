package com.coffee.pos.controller;

import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.model.Goods;
import com.coffee.pos.service.GoodsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GoodsController {

    @Autowired private GoodsService goodsService;

    @GetMapping("api/v1/goods")
    public List<Goods> getGoods() {
        return goodsService.getAll();
    }

    @GetMapping("api/v1/goods/{id}")
    public Goods getGoodsById(String id) {
        return goodsService.findById(id);
    }

    @PostMapping("api/v1/goods")
    public Goods saveGoods(CreateGoodsDTO goods) {
        return goodsService.save(goods);
    }
}
