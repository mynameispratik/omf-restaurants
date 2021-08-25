package com.tastyfood.omf.restaurants.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tastyfood.omf.restaurants.dto.RestaurantDto;
import com.tastyfood.omf.restaurants.dto.RestaurantPagedList;
import com.tastyfood.omf.restaurants.service.RestaurantService;


@RequestMapping("/api/v1/")
@RestController
public class RestaurantController {
	
	private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

	@Autowired
	private RestaurantService restaurantService;

	@PostMapping
	public ResponseEntity<RestaurantDto> addRestaurant(@RequestBody RestaurantDto restaurantDTO) {
		return new ResponseEntity<RestaurantDto>(restaurantService.addRestaurant(restaurantDTO), HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public  ResponseEntity<RestaurantDto> updateRestaurant(@RequestBody RestaurantDto restaurantDTO, @PathVariable("id") UUID id){
		return new ResponseEntity<RestaurantDto>(restaurantService.updateRestaurant(restaurantDTO,id), HttpStatus.NO_CONTENT);
	}

	@GetMapping(produces = {"appication/json"},path="restaurant")
	public ResponseEntity<RestaurantPagedList> listRestaurant(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
																@RequestParam(value = "pageSize", required = false) Integer pageSize,
																@RequestParam(value = "location", required = false) String location,
																@RequestParam(value = "dish",required = false) String dish,
																@RequestParam(value = "budget", required = false) Double budget,
																@RequestParam(value = "restuarantName", required = false) String restuarantName){
		if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        
        RestaurantPagedList pagedList=restaurantService.listRestaurant(restuarantName,budget,dish,location,PageRequest.of(pageNumber, pageSize));
		return new ResponseEntity<>(pagedList,HttpStatus.OK);
	}

}
