package com.coffee.pos.repository;

import com.coffee.pos.model.GoodsPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsPriceRepository extends JpaRepository<GoodsPrice, String> {}
