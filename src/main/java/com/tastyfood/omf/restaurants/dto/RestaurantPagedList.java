package com.tastyfood.omf.restaurants.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class RestaurantPagedList extends PageImpl<RestaurantDto> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestaurantPagedList(@JsonProperty("content") List<RestaurantDto> content,
                         @JsonProperty("number") int number,
                         @JsonProperty("size") int size,
                         @JsonProperty("totalElements") Long totalElements,
                         @JsonProperty("pageable") JsonNode pageable,
                         @JsonProperty("last") boolean last,
                         @JsonProperty("totalPages") int totalPages,
                         @JsonProperty("sort") JsonNode sort,
                         @JsonProperty("first") boolean first,
                         @JsonProperty("numberOfElements") int numberOfElements) {

        super(content, PageRequest.of(number, size), totalElements);
    }

	@JsonCreator(mode = Mode.PROPERTIES)
    public RestaurantPagedList(List<RestaurantDto> content, Pageable pageable, long total) {
        super(content, pageable, total);
    }

    public RestaurantPagedList(List<RestaurantDto> content) {
        super(content);
    }
}
