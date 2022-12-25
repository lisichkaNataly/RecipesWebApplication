package me.izm.model;

import lombok.Data;

import java.util.*;
@Data
public class Recipe {

    private String name;
    private int cookingTime;
    private List<Ingredient> ingredients;
    private List<String> steps;



}
