package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.domain.Food;
import com.sparta.deliveryapi.domain.FoodOrder;

import com.sparta.deliveryapi.domain.Orders;
import com.sparta.deliveryapi.domain.Restaurant;
import com.sparta.deliveryapi.dto.orderRequest.OrderRequestDto;
import com.sparta.deliveryapi.dto.orderResponse.OrdersDto;
import com.sparta.deliveryapi.repository.FoodOrderRepository;

import com.sparta.deliveryapi.repository.OrdersRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Service
public class OrderService {

    private final FoodOrderRepository foodOrderRepository;
    private final OrdersRepository ordersRepository;
    private final FoodService foodService;


    // 총 주문금액
    public static int totalPrice(Restaurant restaurant, List<FoodOrder> foodOrder){

        int price = 0;

        for(FoodOrder foodOrders : foodOrder){
            price += foodOrders.getPrice();
        }
        if(price < restaurant.getMinOrderPrice()){
            throw new IllegalArgumentException("예외");
        }
        return price+restaurant.getDeliveryFee();

    }

    // 주문하기
    @Transactional
    public List<FoodOrder> foodOrder(OrderRequestDto orderRequestDto) {

        List<FoodOrder> foodOrderList = new ArrayList<>();

        for(int i = 0; i < orderRequestDto.getFoods().size(); i++){
            int quantity = orderRequestDto.getFoods().get(i).getQuantity();


            if(quantity < 1 || quantity > 100){
                throw new IllegalArgumentException("잘못된 수량입니다.");
            }
            Long id = orderRequestDto.getFoods().get(i).getId();
            Food food = foodService.foodDetail(id);


            FoodOrder foodOrder = new FoodOrder(food, quantity);

            foodOrderList.add(foodOrder);
        }
        return foodOrderList;

    }
    // 조회
    @Transactional
    public List<OrdersDto> findAllOrder() {

     List<Orders> orders = ordersRepository.findAll();
     List<OrdersDto> ordersDtos = new ArrayList<>();

     for(Orders order : orders){
         OrdersDto ordersDto = new OrdersDto(order);
         ordersDtos.add(ordersDto);
     }
     return ordersDtos;
    }
}