package com.example.fitnesstracker.controller;

import com.example.fitnesstracker.entity.DailyProgress;
import com.example.fitnesstracker.service.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/progress")
public class ProgressController {

    @Autowired
    private ProgressService progressService;

    // Fetch progress for a specific day
    @GetMapping("/daily")
    public DailyProgress getDailyProgress(@RequestParam String username, @RequestParam String date) {
        return progressService.getDailyProgress(username, date);
    }

    // Get AI suggestions for improving progress
    @GetMapping("/suggestions")
    public String getSuggestions(@RequestParam String username) {
        return progressService.getSuggestions(username);
    }
}
