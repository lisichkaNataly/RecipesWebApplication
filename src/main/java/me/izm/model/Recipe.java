package me.izm.model;

import java.util.*;

public class Recipe {

    private final String id;
    private final String name;
    private int cookingTime;

    public Recipe(String id, String name, int cookingTime) {
        this.id = id;
        this.name = name;
        this.cookingTime = cookingTime;
    }

    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public String getId() {
        return id;
    }

}
