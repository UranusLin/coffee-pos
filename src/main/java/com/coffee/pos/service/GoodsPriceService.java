package com.coffee.pos.service;

import com.coffee.pos.dto.goods_price.CreateGoodsPriceDTO;
import com.coffee.pos.model.Goods;
import com.coffee.pos.model.GoodsPrice;
import com.coffee.pos.repository.GoodsPriceRepository;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoodsPriceService {
    @Autowired GoodsPriceRepository goodsPriceRepository;

    @Autowired GoodsService goodsService;

    // Create, Read, Update
    public GoodsPrice createGoodsPrice(CreateGoodsPriceDTO createGoodsPriceDTO) {
        GoodsPrice goodsPrice = mapToEntity(createGoodsPriceDTO);
        if (goodsPrice != null) {
            return goodsPriceRepository.save(goodsPrice);
        } else {
            return null;
        }
    }

    public GoodsPrice queryById(String id) {
        return goodsPriceRepository.findById(id).orElse(null);
    }

    private GoodsPrice mapToEntity(CreateGoodsPriceDTO createGoodsPriceDTO) {
        Goods goods = goodsService.findById(createGoodsPriceDTO.getGoods_id().toString());
        if (goods != null) {
            return GoodsPrice.builder()
                    .goods(goods)
                    .price(createGoodsPriceDTO.getPrice())
                    .updateAt(LocalDateTime.now())
                    .createAt(LocalDateTime.now())
                    .build();
        } else {
            return null;
        }
    }
}
