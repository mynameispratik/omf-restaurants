package com.tastyfood.omf.restaurants.dto;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class RestaurantDto {
	
	private UUID id;

	private String restaurantName;

	private String location;

	private String distance;

	private Set<DishDto> dishes;
}
