package com.example.fitnesstracker.service;

import com.example.fitnesstracker.entity.DailyProgress;
import com.example.fitnesstracker.entity.User;
import com.example.fitnesstracker.repository.DailyProgressRepository;
import com.example.fitnesstracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class ProgressService {

    @Autowired
    private DailyProgressRepository dailyProgressRepository;

    @Autowired
    private UserRepository userRepository;

    // Get daily progress for a user
    public DailyProgress getDailyProgress(String username, String date) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        LocalDate progressDate = LocalDate.parse(date);
        Optional<DailyProgress> progress = dailyProgressRepository.findByUserAndDate(user, progressDate);
        return progress.orElseThrow(() -> new IllegalArgumentException("Progress not found for the given date"));
    }

    // Get AI-driven suggestions for a user (mockup for now)
    public String getSuggestions(String username) {
        // Mock implementation, you can replace with AI logic
        return "Based on your activity, try increasing protein intake and doing 20 minutes of cardio.";
    }
}
