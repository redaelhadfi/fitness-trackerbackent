package com.example.fitnesstracker.service;

import com.example.fitnesstracker.entity.User;
import com.example.fitnesstracker.entity.Workout;
import com.example.fitnesstracker.repository.UserRepository;
import com.example.fitnesstracker.repository.WorkoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WorkoutService {

    @Autowired
    private WorkoutRepository workoutRepository;

    @Autowired
    private UserRepository userRepository;

    // Log a workout for a user
    public void logWorkout(Workout workout, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        workout.setUser(user);
        workout.setTimestamp(LocalDateTime.now());
        workoutRepository.save(workout);
    }

    // Get all workouts for a user
    public List<Workout> getWorkoutsForUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return workoutRepository.findByUser(user);
    }

    // Get workouts for a user within a date range
    public List<Workout> getWorkoutsForUserByDateRange(String username, LocalDateTime start, LocalDateTime end) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return workoutRepository.findByUserAndTimestampBetween(user, start, end);
    }
}
