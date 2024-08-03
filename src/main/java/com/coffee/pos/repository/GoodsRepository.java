package com.coffee.pos.repository;

import com.coffee.pos.model.Goods;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
    List<Goods> findByNameContaining(String name);
}
