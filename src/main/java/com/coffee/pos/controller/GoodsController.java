package com.coffee.pos.controller;

import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.model.Goods;
import com.coffee.pos.service.GoodsService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class GoodsController {

    @Autowired private GoodsService goodsService;

    @GetMapping("/goods")
    public List<Goods> getGoods() {
        return goodsService.getAll();
    }

    @GetMapping("/goods/{id}")
    public Goods getGoodsById(@PathVariable String id) {
        return goodsService.findById(id);
    }

    @PostMapping("/goods")
    public Goods saveGoods(@RequestBody CreateGoodsDTO goods) {
        return goodsService.save(goods);
    }
}
