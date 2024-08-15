package com.coffee.pos.service;

import com.coffee.pos.dto.CommonListResponse;
import com.coffee.pos.dto.goods_price.CreateGoodsPriceDTO;
import com.coffee.pos.dto.goods_price.UpdateGoodsPriceDTO;
import com.coffee.pos.enums.CommonStatus;
import com.coffee.pos.model.Goods;
import com.coffee.pos.model.GoodsPrice;
import com.coffee.pos.repository.GoodsPriceRepository;
import java.time.LocalDateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class GoodsPriceService {
    private static final Logger logger = LoggerFactory.getLogger(GoodsPriceService.class);

    @Autowired GoodsPriceRepository goodsPriceRepository;

    @Autowired GoodsService goodsService;

    public ResponseEntity<CommonListResponse<GoodsPrice>> getAll(String name, Pageable pageable) {
        Page<GoodsPrice> goodsPrices;
        if (name != null && !name.isEmpty()) {
            goodsPrices = goodsPriceRepository.findByGoodsNameContaining(name, pageable);
        } else {
            goodsPrices = goodsPriceRepository.findAll(pageable);
        }
        CommonListResponse<GoodsPrice> response =
                new CommonListResponse<>("Success", CommonStatus.SUCCESS, goodsPrices);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

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
        logger.info("[GoodsPrice][queryById] User use {} to search from id.", id);
        return goodsPriceRepository.findById(id).orElse(null);
    }

    public GoodsPrice update(String id, UpdateGoodsPriceDTO updateGoodsPriceDTO) {
        GoodsPrice goodsPrice = queryById(id);
        if (goodsPrice != null) {
            if (updateGoodsPriceDTO.getPrice() != null) {
                goodsPrice.setPrice(updateGoodsPriceDTO.getPrice());
                goodsPrice.setUpdateAt(LocalDateTime.now());
                return goodsPriceRepository.save(goodsPrice);
            } else {
                return goodsPrice;
            }
        } else {
            return null;
        }
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
