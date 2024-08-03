package com.coffee.pos.controller;

import com.coffee.pos.dto.CommonListResponse;
import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.Goods;
import com.coffee.pos.service.GoodsService;
import java.util.List;

import org.aspectj.bridge.IMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/goods")
public class GoodsController {

    @Autowired private GoodsService goodsService;

    @GetMapping("")
    public ResponseEntity<CommonListResponse<Goods>> getGoods() {
        List<Goods> goodsList = goodsService.getAll();
        CommonListResponse<Goods> response = new CommonListResponse<>("Success", CommonStatus.SUCCESS, goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonObjectResponse<Goods>> getGoodsById(@PathVariable String id) {
        Goods goods = goodsService.findById(id);
        CommonObjectResponse<Goods> response = new CommonObjectResponse<>("Success", CommonStatus.SUCCESS, goods);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonObjectResponse<Goods>> saveGoods(@RequestBody CreateGoodsDTO goods) {
        Goods newGoods = goodsService.save(goods);
        CommonObjectResponse<Goods> response = new CommonObjectResponse<>("Success", CommonStatus.SUCCESS, newGoods);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
