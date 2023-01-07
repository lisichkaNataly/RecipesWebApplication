package me.izm.service;

import me.izm.model.Ingredient;
import me.izm.model.Recipe;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface RecipeService {
    Recipe add(Recipe recipe);

    Recipe get(Long id);

    List<Recipe> getAll();

    Recipe update(long id, Recipe recipe);

    Recipe remove(long id);

    void addRecipesFromInputStream(InputStream inputStream) throws IOException;

    File createRecipesTxtFile();
}
