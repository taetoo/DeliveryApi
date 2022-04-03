package com.sparta.deliveryapi.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodRequestDto {
    private Long RestaurantId;
    private String name;
    private int price;
}
