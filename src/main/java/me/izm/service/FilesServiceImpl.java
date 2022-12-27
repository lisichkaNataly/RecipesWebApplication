package me.izm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class FilesServiceImpl implements FilesService {

    @Value("${path.to.ingredient.file}")
    private String ingredientFilePath;

    @Value("${name.of.ingredient.file}")
    private String ingredientFileName;

    @Override
    public boolean saveToFile(String json) {
        try {
            cleanDataFile();
            Files.writeString(Path.of(ingredientFilePath, ingredientFileName), json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    @Override
    public String readFromFile() {
        try {
           return Files.readString(Path.of(ingredientFilePath, ingredientFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean cleanDataFile() {
        try {
            Path path = Path.of(ingredientFilePath, ingredientFileName);
            Files.deleteIfExists(path);
            Files.createFile(path);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
