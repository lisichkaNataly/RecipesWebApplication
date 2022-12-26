package me.izm.service;

import org.springframework.stereotype.Service;


public interface RecipeFilesService {
    boolean saveToFile(String json);

    String readFromFile();
}
