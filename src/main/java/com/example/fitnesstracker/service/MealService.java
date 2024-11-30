package com.example.fitnesstracker.service;

import com.example.fitnesstracker.entity.Meal;
import com.example.fitnesstracker.entity.User;
import com.example.fitnesstracker.repository.MealRepository;
import com.example.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MealService {

    @Autowired
    private MealRepository mealRepository;

    @Autowired
    private UserRepository userRepository;

    // Log a meal for a user
    public void logMeal(Meal meal, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        meal.setUser(user);
        meal.setTimestamp(LocalDateTime.now());
        mealRepository.save(meal);
    }

    // Get all meals for a user
    public List<Meal> getMealsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mealRepository.findByUser(user);
    }

    // Get meals for a user within a date range
    public List<Meal> getMealsForUserByDateRange(String username, LocalDateTime start, LocalDateTime end) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return mealRepository.findByUserAndTimestampBetween(user, start, end);
    }
}
