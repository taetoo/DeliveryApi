package com.sparta.deliveryapi.domain;

import com.sparta.deliveryapi.dto.FoodRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Food {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id; //Food_id //PK 변하지않는 유니크한 키값

    @Column(nullable = false)
    private Long restaurantId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int price;

    // 사용은 가능한데 속성을 바꿀순 없어, 프라이빗은 같은 클래스 안에서만 쓰일 수 있기 때문에, 다른 클래스에서 사용하기위해 생성해줌
    public Food(Long restaurantId, String name, int price) {
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;

    }
}
