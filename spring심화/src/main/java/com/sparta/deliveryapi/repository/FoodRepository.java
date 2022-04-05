package com.sparta.deliveryapi.repository;
import com.sparta.deliveryapi.domain.Food;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // 데이터베이스에서 어떻게 원하는 정보를 가져올지 정해주는 인터페이스;
    // jpa findBy 메서드 사용시 메인 클래스의 변수명과 일치시켜야 한다.
    // 즉 Repository 에서는 메서드를 정의해주는 역할을 하는 것임.
    Optional <Food> findByRestaurantIdAndName(Long restaurantId, String name);
    List<Food> findByRestaurantId(Long restaurantId);

}
