package com.coffee.pos.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "goods_price")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoodsPrice {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "goods_id")
    private Goods goods;

    private Integer price;

    private LocalDateTime createAt;
    private LocalDateTime updateAt;
}
