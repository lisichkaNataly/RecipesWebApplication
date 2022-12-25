package me.izm.service;

import me.izm.model.Ingredient;
import me.izm.model.Recipe;

import java.util.Collection;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe get(Long id);

    List<Recipe> getAll();

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);
}
