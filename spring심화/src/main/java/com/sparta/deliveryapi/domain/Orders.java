package com.sparta.deliveryapi.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@Entity
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

    @OneToMany // Orders 에 여러 주문이 들어와서 취합되기 때문
    @JoinColumn(name = "FOOD_ORDER_ID")
    private List<FoodOrder> foods;



    public Orders(int totalPrice, String restaurantName, int deliveryFee, List<FoodOrder> foods){
        this.totalPrice = totalPrice;
        this.restaurantName = restaurantName;
        this.deliveryFee = deliveryFee;
        this.foods = foods;



    }

}

