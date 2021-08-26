package com.tastyfood.omf.restaurants.controller;

import java.util.List;
import java.util.UUID;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tastyfood.omf.restaurants.dto.DishDto;
import com.tastyfood.omf.restaurants.dto.Menu;
import com.tastyfood.omf.restaurants.service.MenuService;

@RequestMapping("/api/v1/")
@RestController
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menu/{restaurantId}")
	public ResponseEntity<List<Menu>> getMenu(@PathVariable("restaurantId") UUID restaurantId){
		return new ResponseEntity<>(menuService.fetchMenu(restaurantId),HttpStatus.OK);
	}
	
}
