package com.tastyfood.omf.restaurants.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tastyfood.omf.restaurants.dto.DishDto;
import com.tastyfood.omf.restaurants.dto.Menu;
import com.tastyfood.omf.restaurants.entity.Dish;
import com.tastyfood.omf.restaurants.entity.Restaurant;
import com.tastyfood.omf.restaurants.mapper.DishMapper;
import com.tastyfood.omf.restaurants.repository.DishRepository;
import com.tastyfood.omf.restaurants.repository.RestaurantRepository;
import com.tastyfood.omf.restaurants.service.MenuService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuServiceImpl implements MenuService {

	private final RestaurantRepository restaurantRepository;
	private final DishMapper dishMapper;
	
	
	@Override
	public List<Menu> fetchMenu(UUID restaurantId) {
		List<Dish> dishes = null;
		Optional<Restaurant> dishOptional=restaurantRepository.findById(restaurantId);
		
		if(dishOptional.isPresent()) {
			dishes = dishOptional.get().getDishes();
			
		}
		
		return dishes.stream().map(dishMapper::dishToMenu).collect(Collectors.toList());
	}

}
