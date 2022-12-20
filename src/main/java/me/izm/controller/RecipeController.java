package me.izm.controller;

import me.izm.model.Ingredient;
import me.izm.model.Recipe;
import me.izm.service.IngredientService;
import me.izm.service.RecipeService;
import org.apache.coyote.Request;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/recipe")
public class RecipeController {

    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("{id}")
    public Recipe getRecipe(@PathVariable Integer id) {
        return this.recipeService.getRecipeById(id);
    }

    @GetMapping
    public Collection<Recipe> getAllRecipes() {
        return this.recipeService.getAllRecipes();
    }

    @PostMapping
    public Recipe addRecipe(@RequestBody Recipe recipe) {
        return this.recipeService.addRecipe(recipe);
    }

}
