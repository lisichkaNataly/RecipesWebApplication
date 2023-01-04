package me.izm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izm.model.Recipe;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    private FilesServiceRecipe filesServiceRecipe;

    private Map<Long, Recipe> recipeMap = new TreeMap<>();
    public long counter = 0;

    public RecipeServiceImpl(FilesServiceRecipe filesServiceRecipe) {
        this.filesServiceRecipe = filesServiceRecipe;
    }

    @PostConstruct
    private void init() {
        try {
            readRecipeFromFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

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


    @Override
    public void addRecipesFromInputStream(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] array = StringUtils.split(line, '|');
                Recipe recipe = new Recipe(array[0], Integer.valueOf(array[1]), array[2]);
                add(recipe);
            }
        }
    }


    private void saveRecipeToFile() {
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("id", counter);
            map.put("recipes", recipeMap);
            String json = new ObjectMapper().writeValueAsString(map);
            filesServiceRecipe.saveRecipeToFile(json);
        } catch (JsonProcessingException e) {
            throw new CustomException();
        }
    }

    private void readRecipeFromFile() {
        try {
            String json = filesServiceRecipe.readRecipeFromFile();
            recipeMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Recipe>>() {
            });
        } catch (JsonProcessingException e) {
            throw new CustomException();
        }
    }

    @Override
    public File createRecipesTxtFile() {
        Path path = filesServiceRecipe.createTempFile("Recipes");
        try (Writer writer = Files.newBufferedWriter(path, StandardOpenOption.APPEND)){
            for (Recipe recipe : recipeMap.values()) {
                writer.append(recipe.toString());
                writer.append("\n");
            }
        } catch (IOException e) {
            throw new CustomException();
        }
        return path.toFile();
    }


}
