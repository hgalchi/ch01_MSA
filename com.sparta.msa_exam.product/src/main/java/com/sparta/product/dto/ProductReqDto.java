package com.sparta.product.dto;

import com.sparta.product.entity.Product;
import lombok.Data;

@Data
public class ProductReqDto {
    private String name;
    private Integer supplyPrice;

    public static Product toEntity(ProductReqDto dto) {
        return Product.builder().name(dto.name)
                .supplyPrice(dto.supplyPrice).build();
    }
}
