package com.sparta.deliveryapi.controller;

import com.sparta.deliveryapi.domain.Food;
import com.sparta.deliveryapi.dto.FoodRequestDto;
import com.sparta.deliveryapi.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class FoodController {

    private final FoodService foodService;

    @GetMapping("/restaurant/{restaurantId}/foods")
    // 하나의 음식점에 등록된 모든 메뉴를 조회
    public List<Food> findById(@PathVariable Long restaurantId) {
        return foodService.showAllFoodMenu(restaurantId);
    }

    @PostMapping("/restaurant/{restaurantId}/food/register")
    // @PathVariable는 path의 id값을 받아오기 위해 쓰임, @Requestbody는 요청본문의 내용을 자바객체로변환 //매개변수를 List형태의 FoodRequestDto
    // 여기서 매개변수를 List 형태로 받는 이유는 테스트 코드를 참고
    // 컨트롤러 메서드랑 서비스 메서드랑 별개
    //메서드 안에 입력받은 요청을 적어둔 매개변수
    public void addFood(@PathVariable Long restaurantId, @RequestBody List <FoodRequestDto> foodRequestDtos) {
       // foodService에 있는 addFood 메서드에 restaurantId와 foodRequestDtos를 받아와서 실행시킨다.
        foodService.addFood(restaurantId, foodRequestDtos);
    }

}
