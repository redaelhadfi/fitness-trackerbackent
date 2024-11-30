package com.example.fitnesstracker.repository;

import com.example.fitnesstracker.entity.User;
import com.example.fitnesstracker.entity.Workout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface WorkoutRepository extends JpaRepository<Workout, Long> {

    // Fetch all workouts for a specific user
    List<Workout> findByUser(User user);

    // Fetch workouts for a user within a specific date range
    List<Workout> findByUserAndTimestampBetween(User user, LocalDateTime start, LocalDateTime end);
}
