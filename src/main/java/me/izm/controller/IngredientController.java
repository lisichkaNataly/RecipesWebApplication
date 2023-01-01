package me.izm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.izm.model.Ingredient;
import me.izm.model.Recipe;
import me.izm.service.IngredientServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;

@RestController
@Tag(name = "Ингредиенты", description = "CRUD операции и другие эндпоинты для работы с ингредиентами")
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientServiceImpl ingredientService;


    public IngredientController(IngredientServiceImpl ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("{id}")
    @Operation(summary = "Поиск ингредиента", description = "нужно искать ингредиент по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ингредиент был найден"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public Ingredient getIngredient(@PathVariable long id) {
        return this.ingredientService.get(id);
    }

    @PostMapping
    @Operation(summary = "Добавление ингредиента",
            description = "для добавления ингредиента требуется названиие, количество и единица измерения")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ингредиент был успешно добавлен",
            content = {@Content(mediaType = "application/json")})})
    public ResponseEntity<?> addIngredient(@RequestBody Ingredient ingredient) {
        if (StringUtils.isBlank(ingredient.getName())) {
            return ResponseEntity.badRequest().body("Название ингредиента не может быть пустым");
        }
        return ResponseEntity.ok(ingredientService.add(ingredient));
    }
    @GetMapping
    @Operation(summary = "Получение всех ингредиентов",
            description = "получение списка List всех ингредиентов")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ингредиенты были успешно получены",
            content = {@Content(mediaType = "формат List")})})
    public List<Ingredient> getAll() {
        return this.ingredientService.getAll();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование ингредиента",
            description = "можно редактировать по id как один параметр, так и несколько в том числе название, количество, единицу измерения")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ингредиент был успешно отредактирован"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public Ingredient updateIngredient(@PathVariable("id") long id, @RequestBody Ingredient ingredient) {
        return ingredientService.update(id, ingredient);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление ингредиента",
            description = "можно удалить ингредиент только по id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "ингредиент успешно удален"),
            @ApiResponse(responseCode = "400", description = "есть ошибка в параметрах запроса"),
            @ApiResponse(responseCode = "404", description = "URL неверный или такого действия нет в веб-приложении"),
            @ApiResponse(responseCode = "500", description = "во время выполнения запроса произошла ошибка на сервере")})
    public Ingredient deleteIngredient(@PathVariable("id") long id) {
        return ingredientService.remove(id);
    }
}
