package com.sparta.deliveryapi.dto.orderResponse;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodOrderDto {
    private String name;
    private int price;
    private int quantity;
}

// FoodOrder로 받는 아이들