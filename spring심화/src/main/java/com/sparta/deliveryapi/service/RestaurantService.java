package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.domain.Restaurant;
import com.sparta.deliveryapi.dto.RestaurantRequestDto;
import com.sparta.deliveryapi.repository.RestaurantRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Service
public class RestaurantService {
    // 멤버변수를 만들어서 repository를 해서 데이터를 찾자
    // repository 를 이용할 때 final 을 사용해서 꼭 필요하다는 것을
    // spring 에게 알려줘야 한다.
    private final RestaurantRepository restaurantRepository;

    @Transactional
    public Restaurant addRestaurant(@RequestBody RestaurantRequestDto requestDto) {

        int minOrderPrice = requestDto.getMinOrderPrice();
        int deliveryFee = requestDto.getDeliveryFee();


        if(minOrderPrice < 1000 || 100000 < minOrderPrice){
            throw new IllegalArgumentException("적절한 금액을 입력해주세요");
        }
        if(minOrderPrice % 100 != 0){
            throw new IllegalArgumentException("적절한 금액을 입력해주세요");
        }
        if(deliveryFee < 0 || 10000 < deliveryFee){
            throw new IllegalArgumentException("적절한 금액을 입력해주세요");
        }
        if(deliveryFee % 500 != 0){
            throw new IllegalArgumentException("적절한 금액을 입력해주세요");
        }

        Restaurant restaurant = new Restaurant(requestDto);
        return restaurantRepository.save(restaurant);
    }

    @Transactional
    public List<Restaurant> showRestaurant() {
        return restaurantRepository.findAll();
    }

}
