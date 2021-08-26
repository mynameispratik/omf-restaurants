package com.tastyfood.omf.restaurants.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tastyfood.omf.restaurants.entity.Dish;



@Repository
public interface DishRepository extends JpaRepository<Dish, UUID> {



}
