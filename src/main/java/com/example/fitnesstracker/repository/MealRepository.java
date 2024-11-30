package com.example.fitnesstracker.repository;

import com.example.fitnesstracker.entity.Meal;
import com.example.fitnesstracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MealRepository extends JpaRepository<Meal, Long> {

    // Fetch all meals for a specific user
    List<Meal> findByUser(User user);



    // Fetch all meals for a specific user within a date range
    List<Meal> findByUserAndTimestampBetween(User user, LocalDateTime start, LocalDateTime end);
}
