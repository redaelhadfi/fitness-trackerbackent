package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.entity.Workout;
import com.example.fitnesstracker.service.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {

    @Autowired
    private WorkoutService workoutService;

    // Log a workout
    @PostMapping("/log")
    public String logWorkout(@RequestBody Workout workout, @RequestParam String username) {
        workoutService.logWorkout(workout, username);
        return "Workout logged successfully!";
    }

    @GetMapping("/all")
    public List<Workout> getWorkouts(@RequestParam String username) {
        return workoutService.getWorkoutsForUser(username);
    }
}
