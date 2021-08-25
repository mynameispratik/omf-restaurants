package com.tastyfood.omf.restaurants.service;

import java.util.UUID;

import org.springframework.data.domain.PageRequest;

import com.tastyfood.omf.restaurants.dto.RestaurantDto;
import com.tastyfood.omf.restaurants.dto.RestaurantPagedList;



public interface RestaurantService {

	RestaurantDto addRestaurant(RestaurantDto restaurantDTO);

	RestaurantDto updateRestaurant(RestaurantDto restaurantDTO, UUID id);

	RestaurantPagedList listRestaurant(String restuarantName, Double budget, String cuisine, String location,
			PageRequest of);

}
