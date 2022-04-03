package com.sparta.deliveryapi.dto;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class RestaurantRequestDto {
    private String name;
    private int minOrderPrice;
    private int deliveryFee;
}
