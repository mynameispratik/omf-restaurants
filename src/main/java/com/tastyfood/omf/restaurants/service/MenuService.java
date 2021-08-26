package com.tastyfood.omf.restaurants.service;

import java.util.List;
import java.util.UUID;

import com.tastyfood.omf.restaurants.dto.DishDto;
import com.tastyfood.omf.restaurants.dto.Menu;

public interface MenuService {

	List<Menu> fetchMenu(UUID restaurantId);

}
