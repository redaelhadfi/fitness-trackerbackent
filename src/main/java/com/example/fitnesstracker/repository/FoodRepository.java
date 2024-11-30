package com.example.fitnesstracker.repository;

import com.example.fitnesstracker.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByDescriptionContainingIgnoreCase(String query);
}
