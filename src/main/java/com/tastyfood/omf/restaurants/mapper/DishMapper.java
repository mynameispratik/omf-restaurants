package com.tastyfood.omf.restaurants.mapper;

import org.mapstruct.Mapper;

import com.tastyfood.omf.restaurants.dto.DishDto;
import com.tastyfood.omf.restaurants.dto.Menu;
import com.tastyfood.omf.restaurants.entity.Dish;

@Mapper
public interface DishMapper {

	DishDto dishToDishDto(Dish dish);
	
	Menu dishToMenu(Dish dish);
	
}
