// src/main/java/com/example/fitnesstracker/dto/FoodDTO.java
package com.example.fitnesstracker.dto;

public class FoodDTO {
    private String description;
    private double protein;
    private double fat;
    private double carbs;
    private double calories;
    private Long id;

    public FoodDTO(Long id, String description, double protein, double fat, double carbs, double calories) {
        this.id = id;
        this.description = description;
        this.protein = protein;
        this.fat = fat;
        this.carbs = carbs;
        this.calories = calories;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }


    // Getters and Setters

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(double carbs) {
        this.carbs = carbs;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }
}
