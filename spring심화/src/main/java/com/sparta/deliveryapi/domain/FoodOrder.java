package com.sparta.deliveryapi.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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

    // 테이블변수에 값을 저장하기 위해
    public FoodOrder(int quantity, int price, String name) {
        this.quantity = quantity;
        this.price = price;
        this.name = name;


    }

}
