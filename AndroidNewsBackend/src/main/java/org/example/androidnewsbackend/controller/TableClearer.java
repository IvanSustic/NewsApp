package org.example.androidnewsbackend.controller;

import lombok.AllArgsConstructor;
import org.example.androidnewsbackend.service.RefreshTokenService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TableClearer implements CommandLineRunner {
    private RefreshTokenService refreshTokenService;
    @Override
    public void run(String... args) throws Exception {
        refreshTokenService.deleteAll();

    }

}