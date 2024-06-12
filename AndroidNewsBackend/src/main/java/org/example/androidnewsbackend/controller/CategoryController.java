package org.example.androidnewsbackend.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.androidnewsbackend.dto.KategorijaDTO;
import org.example.androidnewsbackend.service.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/kategorija")
@AllArgsConstructor
@Slf4j
public class CategoryController {
    private CategoryService categoryService;
    @GetMapping("/all")
    public List<KategorijaDTO> getAllNews() {

        log.info("Get all news called");

        return categoryService.findAll();
    }
}
