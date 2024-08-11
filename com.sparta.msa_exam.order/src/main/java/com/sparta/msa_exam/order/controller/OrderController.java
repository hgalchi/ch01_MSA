package com.sparta.msa_exam.order.controller;

import com.sparta.msa_exam.order.dto.OrderResDto;
import com.sparta.msa_exam.order.service.OrderDetailService;
import com.sparta.msa_exam.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

    @Value("${server.port}")
    private String port;

    //주문 추가
    @PostMapping
    public ResponseEntity addOrder(@RequestParam String name) {
        orderService.addOrder(name);
        return ResponseEntity.ok()
                .headers(getHeaders())
                .build();
    }

    //주문에 상품을 추가
    @PutMapping("/{orderId}")
    public ResponseEntity addProductToOrder(@PathVariable Long orderId,
                                            @RequestParam String productId) {
        orderDetailService.addProductToOrder(orderId, Long.parseLong(productId));
        return ResponseEntity.ok()
                .headers(getHeaders())
                .build();
    }

    //주문 단건 조회
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResDto> getOrderById(@PathVariable Long orderId) {
        OrderResDto res = orderService.getOrderById(orderId);
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
