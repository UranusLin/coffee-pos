package com.coffee.pos.repository;

import com.coffee.pos.model.Goods;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
    Goods findByName(String name);
}
