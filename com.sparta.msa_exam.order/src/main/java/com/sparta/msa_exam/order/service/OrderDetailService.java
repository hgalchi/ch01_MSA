package com.sparta.msa_exam.order.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.msa_exam.order.client.ProductClient;
import com.sparta.msa_exam.order.dto.ProductResDto;
import com.sparta.msa_exam.order.entity.OrderDetails;
import com.sparta.msa_exam.order.entity.Orders;
import com.sparta.msa_exam.order.repository.OrderDetailsRepository;
import com.sparta.msa_exam.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OrderDetailService {
    private final OrderDetailsRepository orderDetailsRepository;
    private final OrderRepository orderRepository;
    private final ProductClient productClient;


    public void addProductToOrder(Long orderId, Long productId) {
        Orders order=orderRepository.findById(orderId).get();
        //product가 상품에 존재하는지 확인
        if(checkProduct(productId)){
            OrderDetails orderDetails = new OrderDetails().builder().order(order).productId(productId).build();
            orderDetailsRepository.save(orderDetails);
        }
    }

    public boolean checkProduct(Long productId) {
        List<ProductResDto> res= productClient.getProducts().getBody();
        return res.stream().anyMatch(t -> Objects.equals(t.getProductId(), productId));

    }
}
