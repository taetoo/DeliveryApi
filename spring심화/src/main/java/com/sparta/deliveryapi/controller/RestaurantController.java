package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.domain.Restaurant;
import com.sparta.deliveryapi.dto.RestaurantRequestDto;
import com.sparta.deliveryapi.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    // 등록된 모든 음식점을 조회
    public List<Restaurant> findAllRestaurant() {
        return restaurantService.showRestaurant();
    }

    @PostMapping("/restaurant/register")
    // 음식점 등록                    //@Requestbody는 요청 본문을 자바객체로변환 //매개변수를 Dto //컨트롤러 메서드랑 서비스 메서드랑 별개
    public Restaurant addRestaurant(@RequestBody RestaurantRequestDto requestDto) {
        return restaurantService.addRestaurant(requestDto);
    }

}
