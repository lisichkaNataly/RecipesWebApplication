package me.izm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izm.model.Ingredient;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

@Service
public class IngredientServiceImpl implements IngredientService{

    private final FilesService filesService;
    private Map<Long, Ingredient> ingredientMap = new LinkedHashMap<>();
    public long counter = 0;

    public IngredientServiceImpl(FilesService filesService) {
        this.filesService = filesService;
    }

    @PostConstruct
    private void init() {
        readFromFile();
    }

    @Override
    public Ingredient add(Ingredient ingredient) {
        ingredientMap.put(this.counter++, ingredient);
        saveToFile();
        return ingredient;
    }

    @Override
    public Ingredient get(long id) {
        return ingredientMap.get(id);
    }

    @Override
    public List<Ingredient> getAll() {
        return new ArrayList<>(this.ingredientMap.values());
    }

    @Override
    public Ingredient update(long id, Ingredient ingredient) {
        if (ingredientMap.containsKey(id)) {
            saveToFile();
            return ingredientMap.put(id, ingredient);
        }
        return null;
    }

    @Override
    public Ingredient remove(long id) {
        return ingredientMap.remove(id);
    }



        private void saveToFile() {
        try {
            String json = new ObjectMapper().writeValueAsString(ingredientMap);
            filesService.saveToFile(json);
        } catch (JsonProcessingException e) {
            throw new CustomException();
        }
    }


    private void readFromFile() {
        String json =  filesService.readFromFile();
        try {
            ingredientMap = new ObjectMapper().readValue(json, new TypeReference<LinkedHashMap<Long, Ingredient>>() {
            });
        } catch (JsonProcessingException e) {
            throw new CustomException();
        }
    }



}
