package com.tastyfood.omf.restaurants.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
public class DishDto {
	private UUID id;

	private String dishName;

	private BigDecimal price;
	
	private int quantity;
	
	
}
