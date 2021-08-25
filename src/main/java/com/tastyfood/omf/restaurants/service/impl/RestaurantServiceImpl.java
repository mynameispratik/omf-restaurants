package com.tastyfood.omf.restaurants.service.impl;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.tastyfood.omf.restaurants.dto.RestaurantDto;
import com.tastyfood.omf.restaurants.dto.RestaurantPagedList;
import com.tastyfood.omf.restaurants.entity.Restaurant;
import com.tastyfood.omf.restaurants.mapper.RestaurantMapper;
import com.tastyfood.omf.restaurants.repository.RestaurantRepository;
import com.tastyfood.omf.restaurants.service.RestaurantService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RestaurantServiceImpl implements RestaurantService {

	private final RestaurantRepository restaurantRepository;
	private final RestaurantMapper restaurantMapper;

	@Override
	public RestaurantDto addRestaurant(RestaurantDto restaurantDTO) {
		return null;
	}

	@Override
	public RestaurantDto updateRestaurant(RestaurantDto restaurantDTO, UUID id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RestaurantPagedList listRestaurant(String restaurantName, Double budget, String dish, String location,
			PageRequest pageRequest) {
		RestaurantPagedList pagedList = null;
		Page<Restaurant> restuarantPage = null;

		if ((!restaurantName.isEmpty()) && (!location.isEmpty())) {
			restuarantPage = restaurantRepository.findByRestaurantNameAndLocation(restaurantName, location,
					pageRequest);
		} else if ((!restaurantName.isEmpty())) {
			restuarantPage = restaurantRepository.findAllByRestaurantName(restaurantName, pageRequest);
		} else if ((!location.isEmpty())) {
			restuarantPage = restaurantRepository.findAllByLocation(location, pageRequest);
		} else {
			restuarantPage = restaurantRepository.findAll(pageRequest);
		}

		if ((!dish.isEmpty() && dish != null) && (budget !=null)) {
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream().filter(rest -> rest.getDishes().stream().anyMatch(
							i -> i.getPrice().equals(budget) && i.getPrice().equals(BigDecimal.valueOf(budget))))
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());
		}

		else if (budget!=null) {
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream()
							.filter(rest -> rest.getDishes().stream()
									.anyMatch(i -> i.getPrice().equals(BigDecimal.valueOf(budget))))
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());

		} else if ((!dish.isEmpty() && dish != null)) {
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream()
							.filter(rest -> rest.getDishes().stream().anyMatch(i -> i.getDishName().equals(dish)))
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());
		}else {
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream()
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());
		}

		return pagedList;
	}

}
