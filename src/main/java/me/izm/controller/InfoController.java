package me.izm.controller;

import me.izm.record.InfoRecord;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class InfoController {
    @GetMapping
    public String index() {
        return "Application is run";
    }

    @GetMapping("/info")
    public InfoRecord info() {
        return new InfoRecord("Natalya Izmaylova",
                "Рецепты", LocalDate.of(2022, 12, 24), "Книга рецептов");
    }
}
