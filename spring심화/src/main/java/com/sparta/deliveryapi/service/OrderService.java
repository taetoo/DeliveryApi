package com.sparta.deliveryapi.service;

import com.sparta.deliveryapi.domain.Food;
import com.sparta.deliveryapi.domain.FoodOrder;

import com.sparta.deliveryapi.domain.Orders;
import com.sparta.deliveryapi.domain.Restaurant;
import com.sparta.deliveryapi.dto.orderRequest.OrderRequestDto;
import com.sparta.deliveryapi.dto.orderResponse.FoodOrderDto;
import com.sparta.deliveryapi.dto.orderResponse.OrdersDto;
import com.sparta.deliveryapi.repository.FoodOrderRepository;

import com.sparta.deliveryapi.repository.FoodRepository;
import com.sparta.deliveryapi.repository.OrdersRepository;
import com.sparta.deliveryapi.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final RestaurantRepository restaurantRepository;
    private final FoodRepository foodRepository;
    private final FoodOrderRepository foodOrderRepository;
    private final OrdersRepository ordersRepository;



    // 주문하기
    @Transactional
    public OrdersDto doOrder(OrderRequestDto orderRequestDto) {

        // OrderRequestDto 에 있는 restaurantId를 통해 restaurantName, deliveryFee 값 불러오기
        Restaurant restaurant = restaurantRepository.findById(orderRequestDto.getRestaurantId()).orElseThrow(
                () -> new NullPointerException("해당 음식점이 존재하지 않습니다.")
        ); // 예외처리의 이유? null 값이 나와서 예외처리는 id 값으로 불러올 떄 기본값
        String restaurantName = restaurant.getName();
        int deliveryFee = restaurant.getDeliveryFee();

        // totalPrice 선언 이 함수에서 실행해야하는 값이기 떄문에
        int totalPrice = 0;


        // OrderDto와 Orders에 들어갈 FoodOrderDto 리스트가 필요하기 때문에 각각 생성
        // 리턴할 타입의 리스트 생성
        List<FoodOrderDto> foodOrderDto = new ArrayList<>();

        // 디비에 저장할 리스트 생성
        List<FoodOrder> foodOrder = new ArrayList<>();

        // OrderRequestDto > foods(FoodOrderRequestDto)의 id값을 통해 name, price 그리고 quantity 값 불러오기
        for (int i=0; i < orderRequestDto.getFoods().size(); i++) {
            // 조건문을 돌릴 변수들 선언
            Food food = foodRepository.findById(orderRequestDto.getFoods().get(i).getId()).orElseThrow(
                    () -> new NullPointerException("해당 음식이 존재하지 않습니다.")
            );
            String name = food.getName();

            int quantity = orderRequestDto.getFoods().get(i).getQuantity();
            int price = food.getPrice() * quantity;

            if(quantity < 1 || quantity > 100){
                throw new IllegalArgumentException("잘못된 수량입니다.");
            }

            totalPrice += price;

            // FoodOrder 리스트에 값 넣어주기 -> 테이블에 저장
            FoodOrder foodOrder1 = new FoodOrder(quantity, price, name);
            foodOrder.add(foodOrder1);
            foodOrderRepository.save(foodOrder1);

            // FoodOrderDto 리스트에 값 넣어주기 -> 리턴용이기 때문에 저장은 X  리스폰스바디에 넣어주기 위해
            FoodOrderDto foodOrderDto1 = new FoodOrderDto(name,price,quantity);
            foodOrderDto.add(foodOrderDto1);
        }

        if (restaurant.getMinOrderPrice() > totalPrice) {
            throw new IllegalArgumentException("최소 주문가격을 맞춰주세요.");
        }
        //여기서 totalPrice는 이미 price(price*quantity)가 포함되어 있음
        totalPrice += deliveryFee;


        // 리턴해줄 OrdersDto 생성
        OrdersDto orderList = new OrdersDto(restaurantName, foodOrderDto, deliveryFee, totalPrice);
        // Orders 테이블에 넣어줄 Orders 객체 생성 후 레포지토리에 저장
        Orders orders = new Orders(totalPrice, restaurantName, deliveryFee, foodOrder);
        ordersRepository.save(orders);

        return orderList;
    }

    // 모든 주문요청 조회
    @Transactional
    public List<Orders> findAllOrder() {
        return ordersRepository.findAll();
    }

}