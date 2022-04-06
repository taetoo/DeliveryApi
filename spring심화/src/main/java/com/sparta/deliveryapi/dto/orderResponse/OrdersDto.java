package com.sparta.deliveryapi.dto.orderResponse;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class OrdersDto {
    private  String restaurantName;
    private  List<FoodOrderDto> foods;
    private  int deliveryFee;
    private  int totalPrice;

    public OrdersDto(String restaurantName, List<FoodOrderDto> foodOrderDto, int deliveryFee, int totalPrice) {
        this.restaurantName =restaurantName;
        this.foods = foodOrderDto;
        this.deliveryFee = deliveryFee;
        this.totalPrice = totalPrice;
    }
}
