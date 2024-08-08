package com.coffee.pos.dto.goods;

import lombok.Data;

@Data
public class CreateGoodsDTO {
    private String name;
    private String description;
    private Integer amount;
    private String goodsUnit;
}
