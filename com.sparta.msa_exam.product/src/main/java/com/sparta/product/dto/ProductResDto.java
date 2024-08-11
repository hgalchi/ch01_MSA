package com.sparta.product.dto;

import com.sparta.product.entity.Product;
import lombok.Data;

import java.io.Serializable;

@Data
public class ProductResDto implements Serializable {
    private Long productId;
    private String name;
    private Integer supplyPrice;

    public ProductResDto(Product product) {
        this.productId = product.getId();
        this.name = product.getName();
        this.supplyPrice = product.getSupplyPrice();
    }
}
