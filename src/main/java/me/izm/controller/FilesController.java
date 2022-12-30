package me.izm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.izm.service.FilesService;
import me.izm.service.FilesServiceRecipe;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@Tag(name = "Файлы", description = "Операции по работе с файлами рецептов и ингредиентов")
@RequestMapping("files")
public class FilesController {
    private final FilesServiceRecipe filesServiceRecipe;
    private final FilesService filesService;

    public FilesController(FilesServiceRecipe filesServiceRecipe, FilesService filesService) {
        this.filesServiceRecipe = filesServiceRecipe;
        this.filesService = filesService;
    }

    @GetMapping("exportRecipes")
    @Operation(summary = "Скачать рецепты в виде json-файла")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно скачался"),
            @ApiResponse(responseCode = "400", description = "плохой запрос, отправлен некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<InputStreamResource> downloadRecipeFile() throws FileNotFoundException {
        File file = filesServiceRecipe.getRecipeFile();
        if (file.exists()) {
            InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .contentLength(file.length())
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"RecipeLog.json\"")
                    .body(resource);
        } else {

            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("importRecipe")
    @Operation(summary = "Загрузить файл с рецептами", description = "принимает json-файл с рецептами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "плохой запрос, отправлен некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<Void> uploadRecipeFile(@RequestParam MultipartFile file) {
        filesServiceRecipe.cleanRecipeFile();
        File fileRecipe = filesServiceRecipe.getRecipeFile();
        try (FileOutputStream fos = new FileOutputStream(fileRecipe)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @GetMapping("importIngredient")
    @Operation(summary = "Загрузить файл с ингредиентами", description = "принимает json-файл с ингредиентами и заменяет сохраненный на жестком (локальном) диске файл на новый")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "файл успешно загружен"),
            @ApiResponse(responseCode = "400", description = "плохой запрос, отправлен некорректный запрос серверу"),
            @ApiResponse(responseCode = "500", description = "сервер столкнулся с неожиданной ошибкой, которая помешала ему выполнить запрос")})
    public ResponseEntity<Void> uploadIngredientFile(@RequestParam MultipartFile file) {
        filesService.cleanIngredientFile();
        File fileIngredient = filesService.getIngredientFile();
        try (FileOutputStream fos = new FileOutputStream(fileIngredient)){
            IOUtils.copy(file.getInputStream(), fos);
            return ResponseEntity.ok().build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
