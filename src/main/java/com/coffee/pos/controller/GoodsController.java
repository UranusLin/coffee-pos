package com.coffee.pos.controller;

import com.coffee.pos.dto.CommonListResponse;
import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.goods.CreateGoodsDTO;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.Goods;
import com.coffee.pos.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/goods")
public class GoodsController {

    @Autowired private GoodsService goodsService;

    @GetMapping("")
    public ResponseEntity<CommonListResponse<Goods>> getGoods(
            @RequestParam(required = false) String name,
            @RequestParam(required = true, defaultValue = "1") int page,
            @RequestParam(required = true, defaultValue = "10") int size,
            @RequestParam(required = true, defaultValue = "id") String sortBy,
            @RequestParam(required = true, defaultValue = "asc") String sortDirection) {

        Sort.Direction direction =
                sortDirection.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        //        Sort.Direction direction;
        //        if (sortDirection.equalsIgnoreCase("desc")) {
        //            direction = Sort.Direction.DESC;
        //        } else {
        //            direction = Sort.Direction.ASC;
        //        }

        Pageable pageable = PageRequest.of(page - 1, size, Sort.by(direction, sortBy));
        Page<Goods> goodsList;
        if (name != null && !name.isEmpty()) {
            goodsList = goodsService.findByName(name, pageable);
        } else {
            goodsList = goodsService.getAll(pageable);
        }
        CommonListResponse<Goods> response =
                new CommonListResponse<>("Success", CommonStatus.SUCCESS, goodsList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonObjectResponse> getGoodsById(@PathVariable String id) {
        Goods goods = goodsService.findById(id);
        CommonObjectResponse response =
                new CommonObjectResponse("Success", CommonStatus.SUCCESS, goods);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CommonObjectResponse> saveGoods(@RequestBody CreateGoodsDTO goods) {
        Goods newGoods = goodsService.save(goods);
        CommonObjectResponse response =
                new CommonObjectResponse("Success", CommonStatus.SUCCESS, newGoods);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
