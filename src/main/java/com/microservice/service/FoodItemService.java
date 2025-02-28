package com.microservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.microservice.dto.FoodItemDTO;
import com.microservice.models.FoodCataloguePage;
import com.microservice.models.FoodItem;
import com.microservice.models.Restaurant;
import com.microservice.repository.FoodItemRepo;

@Service
public class FoodItemService {
	
	@Autowired
	private FoodItemRepo foodItemRepo;
	
     @Autowired
     private RestTemplate restTemplate;	
	
	 public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
	        FoodItem foodItemSavedInDB = foodItemRepo.save(
	        		FoodItem.builder().itemName(foodItemDTO.getItemName()).id(foodItemDTO.getId())
	        		.itemDescription(foodItemDTO.getItemDescription())
	        		.isVeg(foodItemDTO.isVeg())
	        		.price(foodItemDTO.getPrice())
	        		.quantity(foodItemDTO.getQuantity())
	        		.restaurantId(foodItemDTO.getRestaurantId()).build());
	        
	        return FoodItemDTO.builder().itemDescription(foodItemSavedInDB.getItemDescription())
	        		.itemName(foodItemSavedInDB.getItemName()).id(foodItemSavedInDB.getId())
	        		.isVeg(foodItemSavedInDB.isVeg())
	        		.price(foodItemSavedInDB.getPrice())
	        			.quantity(foodItemSavedInDB.getQuantity())
	        			.restaurantId(foodItemSavedInDB.getRestaurantId()).build();
	    }
	 
	 public FoodCataloguePage fetchFoodCataloguePage(Integer id) {
		 List<FoodItem> foodItems = fetchFoodItemList(id);
		 Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(id);
		 return createFoodCataloguePage(foodItems, restaurant);
	 }

	 private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
	        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
	        foodCataloguePage.setFoodItemsList(foodItemList);
	        foodCataloguePage.setRestaurant(restaurant);
	        return foodCataloguePage;
	    }

	private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
	       return restTemplate.getForObject("http://RestorantListing/restaurant/fetchById/"+restaurantId, Restaurant.class);
	    }

	private List<FoodItem> fetchFoodItemList(Integer id) {
        return foodItemRepo.findByRestaurantId(id);

	}

}
