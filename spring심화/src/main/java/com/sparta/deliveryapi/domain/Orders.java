package com.sparta.deliveryapi.domain;

import com.sparta.deliveryapi.dto.orderResponse.OrdersDto;
import com.sparta.deliveryapi.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //order_id //PK 변하지않는 유니크한 키값

    @Column(nullable = false)
    private int deliveryFee;

    @Column(nullable = false)
    private String restaurantName;

    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(mappedBy = "foodOrders")
    private List<FoodOrder> foodOrderList;



    public Orders(Restaurant restaurant, List<FoodOrder> foodOrderList){
        this.totalPrice = OrderService.totalPrice(restaurant, foodOrderList);
        this.deliveryFee = restaurant.getDeliveryFee();
        this.restaurantName = restaurant.getName();
        this.foodOrderList = foodOrderList;



    }

}

