package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.domain.Orders;
import com.sparta.deliveryapi.dto.orderRequest.OrderRequestDto;
import com.sparta.deliveryapi.dto.orderResponse.OrdersDto;
import com.sparta.deliveryapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;


    // orderRequestDto 에 주문요청 정보로 주문하기
    @PostMapping("/order/request")
    public OrdersDto doOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return orderService.doOrder(orderRequestDto);
    }


    @GetMapping("/orders")
    // 등록된 모든 음식점을 조회
    public List<Orders> findAllOrder() {
        return orderService.findAllOrder();
    }

}
