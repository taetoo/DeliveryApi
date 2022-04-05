package com.sparta.deliveryapi.dto.orderResponse;

import com.sparta.deliveryapi.domain.FoodOrder;
import com.sparta.deliveryapi.domain.Orders;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class OrdersDto {
    private String restaurantName;
    private List<FoodOrderDto> foods;
    private int deliveryFee;
    private int totalPrice;


    public OrdersDto(Orders orders){
        this.restaurantName = orders.getRestaurantName();
        this.deliveryFee = orders.getDeliveryFee();
        this.totalPrice = orders.getTotalPrice();

        List<FoodOrderDto> foodOrderDtos = new ArrayList<>();

        for(FoodOrder food : orders.getFoodOrderList()){
            FoodOrderDto foodOrderDto = new FoodOrderDto();
            foodOrderDto.setName(food.getName());
            foodOrderDto.setPrice(food.getPrice());
            foodOrderDto.setQuantity(food.getQuantity());

            foodOrderDtos.add(foodOrderDto);
        }
        this.setFoods(foodOrderDtos);
    }
}
