package com.sparta.product.controller;

import com.sparta.product.dto.ProductReqDto;
import com.sparta.product.dto.ProductResDto;
import com.sparta.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.hc.client5.http.entity.mime.Header;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RefreshScope
@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Value("${server.port}")
    private String port;

    //상품추가 API
    @PostMapping
    public ResponseEntity addProduct(@RequestBody ProductReqDto dto) {
        productService.addProduct(dto);
        return ResponseEntity.ok()
                .headers(getHeaders())
                .build();
    }


    //상품 목록 조회 API
    @GetMapping
    public ResponseEntity<List<ProductResDto>> getAllProducts() {
        List<ProductResDto> res = productService.getAllProducts();
        return ResponseEntity.ok()
                .headers(getHeaders())
                .body(res);
    }

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Server-Port", port);
        return headers;
    }
}
