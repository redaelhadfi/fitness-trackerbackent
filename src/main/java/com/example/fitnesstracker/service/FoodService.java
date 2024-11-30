package com.example.fitnesstracker.service;

import com.example.fitnesstracker.dto.FoodDTO;
import com.example.fitnesstracker.entity.Food;
import com.example.fitnesstracker.repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<FoodDTO> searchFoods(String query) {
        List<Food> foods = foodRepository.findByDescriptionContainingIgnoreCase(query);
        return foods.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FoodDTO calculateNutrition(Long foodId, double grams) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new IllegalArgumentException("Food not found"));
        return new FoodDTO(
                food.getId(),
                food.getDescription(),
                (food.getProteinPer100g() * grams) / 100,
                (food.getFatPer100g() * grams) / 100,
                (food.getCarbsPer100g() * grams) / 100,
                (food.getCaloriesPer100g() * grams) / 100
        );
    }

    private FoodDTO convertToDTO(Food food) {
        return new FoodDTO(
                food.getId(),
                food.getDescription(),
                food.getProteinPer100g(),
                food.getFatPer100g(),
                food.getCarbsPer100g(),
                food.getCaloriesPer100g()
        );
    }
}
