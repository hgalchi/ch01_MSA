package com.sparta.msa_exam.order.dto;

import lombok.Data;

@Data
public class ProductResDto {
    private Long productId;
    private String name;
    private Integer supplyPrice;

}
