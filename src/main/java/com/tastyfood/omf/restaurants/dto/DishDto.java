package com.tastyfood.omf.restaurants.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class DishDto {
	private UUID id;

	private String dishName;

	private BigDecimal price;
	
	private int quantity;
	
	
}
