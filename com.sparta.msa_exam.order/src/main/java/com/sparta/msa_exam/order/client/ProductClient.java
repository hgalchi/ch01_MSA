package com.sparta.msa_exam.order.client;

import com.sparta.msa_exam.order.dto.ProductResDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name="product-service")
public interface ProductClient {
    @GetMapping("/products")
    ResponseEntity<List<ProductResDto>> getProducts();
}
