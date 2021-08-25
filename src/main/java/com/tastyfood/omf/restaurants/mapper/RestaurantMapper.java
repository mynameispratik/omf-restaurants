package com.tastyfood.omf.restaurants.mapper;

import org.mapstruct.Mapper;

import com.tastyfood.omf.restaurants.dto.RestaurantDto;
import com.tastyfood.omf.restaurants.entity.Restaurant;



@Mapper
public interface RestaurantMapper {

	Restaurant restaurantDtoToRestaurant(RestaurantDto restaurantDto);
	
	RestaurantDto restaurantToRestaurantDto(Restaurant restaurant);
	
}
