package com.tastyfood.omf.restaurants.service.impl;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	private ObjectMapper mapper;

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

		if (( restaurantName != null &&!restaurantName.isEmpty()) && (location!= null &&!location.isEmpty() )) {
			restuarantPage = restaurantRepository.findByRestaurantNameAndLocation(restaurantName, location,
					pageRequest);
		} else if ((!restaurantName.isEmpty()&& restaurantName != null)) {
			restuarantPage = restaurantRepository.findAllByRestaurantName(restaurantName, pageRequest);
		} else if ((location!= null &&!location.isEmpty())) {
			restuarantPage = restaurantRepository.findAllByLocation(location, pageRequest);
		} else {
			restuarantPage = restaurantRepository.findAll(pageRequest);
		}

		if (( dish != null&&!dish.isEmpty() ) && (budget !=null)) {
			BigDecimal decimal= new BigDecimal(budget);
			
			restuarantPage.getContent().stream().forEach(o->o.getDishes().removeIf(i-> !i.getDishName().equals(dish) &&!(i.getPrice().compareTo(decimal)==0)));
			
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream().filter(rest -> rest.getDishes().stream().anyMatch(
							i -> i.getDishName().equals(dish) && i.getPrice().compareTo(decimal)==0 ))
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());
		}

		else if (budget!=null) {
			BigDecimal decimal= new BigDecimal(budget);
			
			restuarantPage.getContent().stream().forEach(o->o.getDishes().removeIf(i-> !(i.getPrice().compareTo(decimal)==0)));
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream()
//							.filter(rest -> rest.getDishes().stream()
//									.anyMatch(i -> i.getPrice().compareTo(decimal)==0 ))
							.map(restaurantMapper::restaurantToRestaurantDto).collect(Collectors.toList()),
					PageRequest.of(restuarantPage.getPageable().getPageNumber(),
							restuarantPage.getPageable().getPageSize()),
					restuarantPage.getTotalElements());

		} else if ((!dish.isEmpty() && dish != null)) {
			restuarantPage.getContent().stream().forEach(o->o.getDishes().removeIf(i-> !i.getDishName().equals(dish)));
			pagedList = new RestaurantPagedList(
					restuarantPage.getContent().stream()
						//	.filter(rest -> rest.getDishes().stream().anyMatch(i -> i.getDishName().equals(dish)))
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
