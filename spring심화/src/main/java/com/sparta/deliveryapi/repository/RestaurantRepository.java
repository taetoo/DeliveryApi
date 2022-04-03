package com.sparta.deliveryapi.repository;

import com.sparta.deliveryapi.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
// spring data jpa 에 상속을 통해
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

}
