package me.izm.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.izm.record.InfoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@Tag(name = "Информация о проекте рецептов", description = "содержит информацию об авторе проекта, описании проекта и даты создания проекта")
public class InfoController {
    @GetMapping
    @Operation(summary = "Запуск приложения", description = "информация о том, что приложение успешно запущено")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "приложение успешно запущено")})
    public String index() {
        return "Application is run";
    }

    @GetMapping("/info")
    @Operation(summary = "Информация о проекте", description = "содержит автора проекта, название проекта и дату создания")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "информация успешно найдена")})
    public InfoRecord info() {
        return new InfoRecord("Natalya Izmaylova",
                "Рецепты", LocalDate.of(2022, 12, 24), "Книга рецептов");
    }
}
