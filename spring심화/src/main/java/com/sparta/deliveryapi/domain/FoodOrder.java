package com.sparta.deliveryapi.domain;

import com.sparta.deliveryapi.dto.orderRequest.FoodOrderRequestDto;
import com.sparta.deliveryapi.dto.orderRequest.OrderRequestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class FoodOrder {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //FoodOrder_id //PK 변하지않는 유니크한 키값

    @Column(nullable = false)
    private int quantity;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false) // 음식 이름
    private String name;

    @ManyToOne
    @JoinColumn(name = "FOOD_ORDER_ID")
    private Orders foodOrders;

    // 테이블변수에 값을 저장하기 위해
    public FoodOrder(Food food, int quantity) {
        this.quantity = quantity;
        this.price = food.getPrice()*quantity;
        this.name = food.getName();


    }

}
