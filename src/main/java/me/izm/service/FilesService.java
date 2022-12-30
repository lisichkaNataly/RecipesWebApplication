package me.izm.service;

import java.io.File;
import java.nio.file.Path;

public interface FilesService {



    boolean saveToFile(String json);

    String readFromFile();


    File getIngredientFile();


    boolean cleanIngredientFile();
}
