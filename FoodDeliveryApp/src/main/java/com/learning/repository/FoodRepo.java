package com.learning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.dto.Food;
@Repository
public interface FoodRepo extends JpaRepository<Food, Long> {
	public List<Food> getAllFood();
}
