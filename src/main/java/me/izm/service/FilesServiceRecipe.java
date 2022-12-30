package me.izm.service;

import java.io.File;

public interface FilesServiceRecipe {
    boolean saveRecipeToFile(String json);


    String readRecipeFromFile();

    File getRecipeFile();

    boolean cleanRecipeFile();
}
