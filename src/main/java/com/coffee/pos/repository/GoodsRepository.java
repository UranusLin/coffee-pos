package com.coffee.pos.repository;

import com.coffee.pos.model.Goods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsRepository extends JpaRepository<Goods, String> {
    Page<Goods> findByNameContaining(String name, Pageable pageable);
}
