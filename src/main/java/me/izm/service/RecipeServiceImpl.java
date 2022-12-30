package me.izm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izm.model.Recipe;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

        private final FilesServiceRecipe filesServiceRecipe;

        private Map<Long, Recipe> recipeMap = new LinkedHashMap<>();
        public long counter = 0;

        public RecipeServiceImpl(FilesServiceRecipe filesServiceRecipe) {
            this.filesServiceRecipe = filesServiceRecipe;
        }

        @PostConstruct
        private void init() {
            readRecipeFromFile();
        }

        @Override
        public Recipe add(Recipe recipe) {
            recipeMap.put(this.counter++, recipe);
            saveRecipeToFile();
            return recipe;
        }

        @Override
        public Recipe get(Long id) {
            return recipeMap.get(id);
        }

        @Override
        public List<Recipe> getAll() {
            return new ArrayList<>(this.recipeMap.values());
        }
        @Override
        public Recipe update(long id, Recipe recipe) {
            if (recipeMap.containsKey(id)) {
                saveRecipeToFile();
                return recipeMap.put(id, recipe);
            }
            return null;
        }

        @Override
        public Recipe remove(long id) {
            return recipeMap.remove(id);
        }

    private void saveRecipeToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(recipeMap);
            filesServiceRecipe.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void readRecipeFromFile() {
        try {
            String json = filesServiceRecipe.readRecipeFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<Map<Long,Recipe>>() {
            });
        } catch (JsonProcessingException e) {
           throw new RuntimeException(e);
        }
    }

    }
