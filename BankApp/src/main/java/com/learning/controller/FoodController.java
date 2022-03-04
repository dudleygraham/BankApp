package com.learning.controller;

import java.util.ArrayList;
import java.util.Collections;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.dto.Food;
import com.learning.enums.FoodType;
import com.learning.exception.NoDataFoundException;
import com.learning.repository.FoodRepo;

import java.util.List;

@RestController//documented, controller, responsebody
@RequestMapping("/food")
@Validated //adds in statement for validation checks for min
public class FoodController {//on port 9015
	@Autowired
	FoodRepo fr;
	@PostMapping(value = "/")
	public ResponseEntity<?> addFood(@Valid @RequestBody Food food){
		Food f = fr.save(food);
		return ResponseEntity.status(201).body(f);
	}
	@GetMapping(value="/{id}")
	public ResponseEntity<?> getFoodByID(@PathVariable("id")@Min(1)long id){
		Food food = fr.findById(id).orElseThrow(()-> new NoDataFoundException("no data for this id"));
		return ResponseEntity.ok(food);
	}
	@GetMapping("/")
	public ResponseEntity<?> getAllFood(){
		java.util.List<Food> list = fr.getAllFood();
		if(list.size()>0) {
			return ResponseEntity.ok(list);
		}
		else throw new NoDataFoundException("no data found");
		
	}
	@PutMapping("/{id}")
	public ResponseEntity<?> updateFood(@PathVariable("id")@Min(1)long id){
		Food food = fr.findById(id).orElseThrow(()-> new NoDataFoundException("no data for this id"));
		return ResponseEntity.ok(food);
	}
	@GetMapping(value="/{food_type}")
	public ResponseEntity<?> getFoodByType(@PathVariable("food_type")FoodType type){
		List<Food> foods = new ArrayList<>();
		List<Food> all = fr.findAll();
		all.forEach(e->{if(e.getFoodType()==type) {foods.add(e);}});
		return ResponseEntity.ok(foods);
	}
	@GetMapping(value="/all/desc")
	public ResponseEntity<?> getAllDescOrder(){
		List<Food> foods = fr.findAll();
		Collections.sort(foods, (a,b)->b.getId().compareTo(a.getId()));
		return ResponseEntity.status(200).body(foods);
	}
	@GetMapping(value="/all/asc")
	public ResponseEntity<?> getAllAscOrder(){
		List<Food> foods = fr.findAll();
		Collections.sort(foods, (a,b)->a.getId().compareTo(b.getId()));
		return ResponseEntity.status(200).body(foods);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFood(@PathVariable("id") long id) {
		if(fr.existsById(id)) {
			fr.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		else throw new NoDataFoundException("no user data");
	}
}//request object contains headers, request line, and request body
//header contains authorization and authentication shit
//request object is passed to once per request filter
//oprf decides to accept or reject based on endpoints and request nature
//if the request is registration, it passes to controller, then repo, then db
//the auth controller returns a response entity
//with logins, it still goes to the auth controller, then auth manager
//authmanager calls password encoder(to compare) and then user details service
//uds retrieves from the repo and db to verify there is a user
//principal and credentials are strings of the auth token
//token is called to store credentials and level?
//passes whole fuckin token back (type, user info, roles) to client
//user is logged in and token is added to header
//when the user is logged in we're on the /auth and the token is validated
//user details are then extracted from the token
//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
//token lol