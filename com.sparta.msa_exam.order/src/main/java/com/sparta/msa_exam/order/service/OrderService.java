package com.sparta.msa_exam.order.service;

import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.OrderResDto;
import com.sparta.msa_exam.order.entity.OrderDetails;
import com.sparta.msa_exam.order.entity.Orders;
import com.sparta.msa_exam.order.repository.OrderDetailsRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;

    public void addOrder(String name) {
        orderRepository.save(new Orders().builder().name(name).build());
    }

    //read 캐싱
    @Cacheable(cacheNames="order",key="args[0]")
    public OrderResDto getOrderById(Long orderId) {
        log.info("read 캐싱");
        List<Integer> products= orderRepository.getOrderByIdWithProducts(orderId);
        return new OrderResDto(orderId,products);
    }

}
