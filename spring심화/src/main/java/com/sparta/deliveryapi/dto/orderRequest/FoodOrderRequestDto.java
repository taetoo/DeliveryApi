package com.sparta.deliveryapi.dto.orderRequest;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FoodOrderRequestDto {
    private Long id; // 음식id
    private int quantity;
}
