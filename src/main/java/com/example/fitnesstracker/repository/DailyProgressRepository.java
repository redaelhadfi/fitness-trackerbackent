package com.example.fitnesstracker.repository;

import com.example.fitnesstracker.entity.DailyProgress;
import com.example.fitnesstracker.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DailyProgressRepository extends JpaRepository<DailyProgress, Long> {

    // Fetch progress for a specific user on a given date
    Optional<DailyProgress> findByUserAndDate(User user, LocalDate date);
}
