package com.tastyfood.omf.restaurants.dto;

import java.util.Set;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class RestaurantDto {
	
	private UUID id;

	private String restaurantName;

	private String location;

	private String distance;

	private Set<DishDto> dishes;
}
