package me.izm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;


    public Recipe(String s, Integer valueOf, String s1) {
    }
}
