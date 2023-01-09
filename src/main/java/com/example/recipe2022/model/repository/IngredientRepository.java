package com.example.recipe2022.model.repository;

import com.example.recipe2022.model.data.Fridge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRepository extends JpaRepository<Fridge, Integer> {
}