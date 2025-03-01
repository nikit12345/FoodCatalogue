package com.microservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.dto.FoodItemDTO;
import com.microservice.models.FoodCataloguePage;
import com.microservice.service.FoodItemService;


@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin(origins = "*")
public class FoodItemController {
	
	@Autowired
	private FoodItemService foodItemService;
	
	  @PostMapping("/addFoodItem")
	    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO){
	        FoodItemDTO foodItemSaved = foodItemService.addFoodItem(foodItemDTO);
	        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
	    }
	  
	  @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
	    public ResponseEntity<FoodCataloguePage> fetchRestauDetailsWithFoodMenu(@PathVariable Integer restaurantId){
	        FoodCataloguePage foodCataloguePage = foodItemService.fetchFoodCataloguePage(restaurantId);
	        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);


	    }

}
