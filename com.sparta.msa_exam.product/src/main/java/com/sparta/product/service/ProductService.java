package com.sparta.product.service;

import com.sparta.product.dto.ProductReqDto;
import com.sparta.product.dto.ProductResDto;
import com.sparta.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    @CachePut(cacheNames="productAll" ,key="targetClass")
    public List<ProductResDto> addProduct(ProductReqDto dto) {
        productRepository.save(ProductReqDto.toEntity(dto));
        return productRepository.findAll().stream()
                .map(ProductResDto::new)
                .collect(Collectors.toList());
    }

    @Cacheable(cacheNames="productAll",key="targetClass")
    public List<ProductResDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductResDto::new)
                .collect(Collectors.toList());
    }
}
