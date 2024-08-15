package com.coffee.pos.controller;

import com.coffee.pos.dto.CommonListResponse;
import com.coffee.pos.dto.CommonObjectResponse;
import com.coffee.pos.dto.goods_price.CreateGoodsPriceDTO;
import com.coffee.pos.dto.goods_price.UpdateGoodsPriceDTO;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.GoodsPrice;
import com.coffee.pos.service.GoodsPriceService;
import com.coffee.pos.utils.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/good_price")
public class GoodsPriceController {
    @Autowired GoodsPriceService goodsPriceService;
    @Autowired CommonUtil commonUtil;

    @GetMapping
    public ResponseEntity<CommonListResponse<GoodsPrice>> getAll(
            @RequestParam(required = false) String name,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Pageable pageable = commonUtil.getPageable(page, size, sortDirection, sortBy);
        return goodsPriceService.getAll(name, pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonObjectResponse> getGoodsPriceById(@PathVariable String id) {
        GoodsPrice goodsPrice = goodsPriceService.queryById(id);
        if (goodsPrice != null) {
            CommonObjectResponse response =
                    new CommonObjectResponse("Success", CommonStatus.SUCCESS, goodsPrice);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<CommonObjectResponse> save(
            @RequestBody CreateGoodsPriceDTO createGoodsPriceDTO) {
        GoodsPrice goodsPrice = goodsPriceService.createGoodsPrice(createGoodsPriceDTO);
        if (goodsPrice != null) {
            return new ResponseEntity<>(
                    new CommonObjectResponse("Success", CommonStatus.SUCCESS, goodsPrice),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommonObjectResponse> update(
            @PathVariable String id, @RequestBody UpdateGoodsPriceDTO updateGoodsPriceDTO) {
        GoodsPrice goodsPrice = goodsPriceService.update(id, updateGoodsPriceDTO);
        if (goodsPrice != null) {
            return new ResponseEntity<>(
                    new CommonObjectResponse("Success", CommonStatus.SUCCESS, goodsPrice),
                    HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
