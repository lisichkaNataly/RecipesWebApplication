package me.izm.service;

public interface FilesServiceRecipe {
    boolean saveRecipeToFile(String json);


    String readRecipeFromFile();
}
