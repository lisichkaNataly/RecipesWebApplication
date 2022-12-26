package me.izm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
@Service
public class RecipeFilesServiceImpl implements RecipeFilesService{
    @Value("${path.to.data.recipe}")
    private String dataFilePathRecipe;

    @Value("${name.of.data.recipe}")
    private String dataFileNameRecipe;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(dataFilePathRecipe, dataFileNameRecipe), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
            return Files.readString(Path.of(dataFilePathRecipe, dataFileNameRecipe));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(dataFilePathRecipe, dataFileNameRecipe);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
