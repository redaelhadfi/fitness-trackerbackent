package com.example.fitnesstracker;

import com.example.fitnesstracker.entity.Food;
import com.example.fitnesstracker.repository.FoodRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class FoodDataInitializer implements CommandLineRunner {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void run(String... args) {
        // Clear existing data and load new data
        foodRepository.deleteAll();  // Delete all entries in the database
        loadFoodData();
    }

    private void loadFoodData() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode foodsArray = mapper.readTree(new File("src/main/resources/food_nutrition_data.json"));

            for (JsonNode foodNode : foodsArray) {
                Food food = parseFoodNode(foodNode);
                if (food != null) {
                    foodRepository.save(food);
                }
            }

            System.out.println("Food data loaded successfully.");
        } catch (IOException e) {
            System.err.println("Error loading food data: " + e.getMessage());
        }
    }

    private Food parseFoodNode(JsonNode foodNode) {
        String description = foodNode.path("description").asText(null);
        if (description == null) {
            System.out.println("Skipping food item with missing description.");
            return null;
        }

        Double protein = foodNode.path("protein").asDouble();
        Double fat = foodNode.path("fat").asDouble();
        Double carbs = foodNode.path("carbs").asDouble();
        Double calories = foodNode.path("calories").asDouble();

        if (protein == null || fat == null || carbs == null || calories == null) {
            System.out.println("Skipping food item due to missing nutrients: " + description);
            return null;
        }

        Food food = new Food();
        food.setDescription(description);
        food.setProteinPer100g(protein);
        food.setFatPer100g(fat);
        food.setCarbsPer100g(carbs);
        food.setCaloriesPer100g(calories);

        return food;
    }
}
