package com.tastyfood.omf.restaurants.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tastyfood.omf.restaurants.entity.Restaurant;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, UUID> {

	Page<Restaurant> findByRestaurantNameAndLocation(String restaurantName, String location, Pageable pageRequest);

//	Page<Restaurant> findByRestaurantName(String restaurantName, PageRequest pageRequest);
//
//	Page<Restaurant> findByLocation(String location, PageRequest pageRequest);

	Page<Restaurant> findAllByRestaurantName(String restaurantName, Pageable pageRequest);

	Page<Restaurant> findAllByLocation(String location, Pageable pageRequest);

}
