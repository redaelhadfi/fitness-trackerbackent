package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.dto.FoodDTO;
import com.example.fitnesstracker.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @Autowired
    private FoodService foodService;

    // Endpoint for searching foods by description
    @GetMapping("/search")
    public List<FoodDTO> searchFoods(@RequestParam("query") String query) {
        return foodService.searchFoods(query);
    }

    // Endpoint for calculating nutrition for a given food ID and weight in grams
    @GetMapping("/calculate")
    public FoodDTO calculateNutrition(@RequestParam("id") Long foodId, @RequestParam("grams") double grams) {
        return foodService.calculateNutrition(foodId, grams);
    }
}
