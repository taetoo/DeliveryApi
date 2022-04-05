package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.domain.Food;
import com.sparta.deliveryapi.dto.FoodRequestDto;
import com.sparta.deliveryapi.repository.FoodRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class FoodService {

    private final FoodRepository foodRepository;
//    private final RestaurantService restaurantService;

    public Food foodDetail(Long id){
       return foodRepository.findById(id).orElseThrow(
               () -> new IllegalArgumentException("없다!")
       );
    }



    @Transactional
    public void addFood(Long restaurantId, @RequestBody List<FoodRequestDto> requestDto) {

        // 1. 리스트 형태이기 때문에 어떤 리스트객체를 불러올지 모르니깐, 순서를 정해서 i 번째의 객체리스트를 불러온것.
        for (int i = 0; i < requestDto.size(); i++) {
            String foodName = requestDto.get(i).getName();
            int foodPrice = requestDto.get(i).getPrice();

            // 레포지토리에서 아이디값이랑 네임을 불러와서 중복확인을 해준다. 레스토랑Id와 음식이름 두개를 셋뚜셋뚜
            // 왜냐하면 한 상호에서 음식을 입력하기 때문에
            Optional<Food> foodList = foodRepository.findByRestaurantIdAndName(restaurantId, foodName);
            // isPresent() = 중복확인 메서드
            if (foodList.isPresent()) {
                throw new IllegalArgumentException("중복된 음식명이 존재합니다.");
            }

            if (100 > foodPrice || 1000000 < foodPrice) {
                throw new IllegalArgumentException("설정 음식 금액이 아닙니다.");
            }
            if (foodPrice % 100 != 0) {
                throw new IllegalArgumentException("100원 단위로 입력해주세요");
            }
            Food food = new Food(restaurantId,foodName,foodPrice);
            foodRepository.save(food);


            // 여기서 dto 객체를 쪼개서 다시 각가의 객체로 나누었기 때문에 쪼개진 각각의 매개변수를 저장해준다.
        }

    }

    // 음식 메뉴 조회
    @Transactional
    public List<Food> showAllFoodMenu(Long restaurantId) {
        return foodRepository.findByRestaurantId(restaurantId);
    }
}
