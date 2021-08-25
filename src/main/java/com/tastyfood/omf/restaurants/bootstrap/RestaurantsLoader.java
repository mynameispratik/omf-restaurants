package com.tastyfood.omf.restaurants.bootstrap;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tastyfood.omf.restaurants.entity.Dish;
import com.tastyfood.omf.restaurants.entity.Restaurant;
import com.tastyfood.omf.restaurants.repository.RestaurantRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RestaurantsLoader implements CommandLineRunner {
	
	private final RestaurantRepository restaurantRepository;
	
	
	@Override
	public void run(String... args) throws Exception {
		
		if(restaurantRepository.count()==0) {
			loadRestaurants();
		}

	}


	private void loadRestaurants() {
		 
		List<Dish> dishes = new ArrayList<>();
		
		Dish dish=Dish.builder()
				.dishName("Vada Pav")
				.price(new BigDecimal(10))
				.quantity(50)
				.build();
		dishes.add(dish);
		
		Dish dish2=Dish.builder()
				.dishName("Pav Bhaaji")
				.price(new BigDecimal(50))
				.quantity(50)
				.build();
		
		dishes.add(dish2);
		
		Restaurant restaurant=Restaurant.builder()
				.restaurantName("Taj")
				.distance("50")
				.location("Mumbai")
				.build();
		
		dishes.forEach(d->d.setRestaurant(restaurant));
		restaurant.setDishes(dishes);
		
		
		
		restaurantRepository.save(restaurant);
		
		List<Dish> dishs = new ArrayList<>();
		
		Dish dish3=Dish.builder()
				.dishName("Biryani")
				.price(new BigDecimal(200))
				.quantity(50)
				.build();
		dishs.add(dish3);
		
		Dish dish4=Dish.builder()
				.dishName("Momos")
				.price(new BigDecimal(100))
				.quantity(50)
				.build();
		
		dishs.add(dish4);
		
		Restaurant restaurant1=Restaurant.builder()
				.restaurantName("MC")
				.distance("50")
				.location("Chennai")
				.build();
		dishs.forEach(d->d.setRestaurant(restaurant1));
		
		restaurant1.setDishes(dishs);
		
		restaurantRepository.save(restaurant1);
	}

}
