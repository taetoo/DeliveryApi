package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.domain.FoodOrder;
import com.sparta.deliveryapi.domain.Orders;
import com.sparta.deliveryapi.domain.Restaurant;
import com.sparta.deliveryapi.dto.orderRequest.OrderRequestDto;
import com.sparta.deliveryapi.dto.orderResponse.OrdersDto;
import com.sparta.deliveryapi.repository.FoodOrderRepository;
import com.sparta.deliveryapi.repository.OrdersRepository;
import com.sparta.deliveryapi.service.OrderService;
import com.sparta.deliveryapi.service.RestaurantService;
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
    private final RestaurantService restaurantService;
    private final OrdersRepository ordersRepository;
    private final FoodOrderRepository foodOrderRepository;


    @PostMapping("/order/request")
    public OrdersDto doOrder(@RequestBody OrderRequestDto orderRequestDto) {
         List <FoodOrder> foodOrderList = orderService.foodOrder(orderRequestDto);
        Restaurant restaurantFindId = restaurantService.restaurantFindId(orderRequestDto.getRestaurantId());

        Orders orders = new Orders(restaurantFindId, foodOrderList);
        ordersRepository.save(orders);

        for(FoodOrder foodOrder : foodOrderList){
            foodOrder.setFoodOrders(orders);
            foodOrderRepository.save(foodOrder);
        }


        OrdersDto ordersDto = new OrdersDto(orders);

        return ordersDto;
    }

    @GetMapping("/orders")
    // 등록된 모든 음식점을 조회
    public List<OrdersDto> findAllOrder() {
        return orderService.findAllOrder();
    }

}
