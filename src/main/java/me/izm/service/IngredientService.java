package me.izm.service;

import me.izm.model.Ingredient;
import me.izm.model.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

public interface IngredientService {
    Ingredient add(Ingredient ingredient);

    Ingredient get(long id);

    List<Ingredient> getAll();

    Ingredient update(long id, Ingredient ingredient);

    Ingredient remove(long id);

}
