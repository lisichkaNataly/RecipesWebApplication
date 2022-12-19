package me.izm.service;

import me.izm.model.Ingredient;
import me.izm.model.Recipe;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
@Service
public class IngredientService {
    private final Map<String, Ingredient> ingredients = new HashMap<>();

    public Ingredient addIngredient(Ingredient ingredient) {
        if (ingredients.containsKey(ingredient.getId())) {
            throw new RuntimeException("Такой элемент уже существует");
        } else {
            ingredients.put(ingredient.getId(), ingredient);
        }
        return ingredient;
    }

    public Ingredient getIngredientById(String id) {
        if (ingredients.containsKey(id)) {
            return ingredients.get(id);
        } else {
            throw new RuntimeException("Элемент не найден");
        }
    }

    public Collection<Ingredient> getAllIngredients() {
        return ingredients.values();
    }

}
