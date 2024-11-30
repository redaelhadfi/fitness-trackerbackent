package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.entity.Meal;
import com.example.fitnesstracker.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/meals")
public class MealController {

    @Autowired
    private MealService mealService;

    // Log a meal for the authenticated user
    @PostMapping("/log")
    public ResponseEntity<Map<String, String>> logMeal(@RequestBody Meal meal, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        mealService.logMeal(meal, username);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Meal logged successfully");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // Get all meals for the authenticated user
    @GetMapping("/all")
    public ResponseEntity<List<Meal>> getMealsForUser(@AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();  // Extract the username
        List<Meal> meals = mealService.getMealsForUser(username);
        return ResponseEntity.ok(meals);
    }

    // Get meals for the authenticated user within a date range
    @GetMapping("/date-range")
    public ResponseEntity<List<Meal>> getMealsForUserByDateRange(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("start") String start,
            @RequestParam("end") String end) {
        String username = userDetails.getUsername();  // Extract the username
        LocalDateTime startDate = LocalDateTime.parse(start);
        LocalDateTime endDate = LocalDateTime.parse(end);
        List<Meal> meals = mealService.getMealsForUserByDateRange(username, startDate, endDate);
        return ResponseEntity.ok(meals);
    }
}
