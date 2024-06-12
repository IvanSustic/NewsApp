package org.example.androidnewsbackend.controller;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.androidnewsbackend.model.UserInfo;
import org.example.androidnewsbackend.service.UserDetailsServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/addUser")
@AllArgsConstructor
@Slf4j
public class UserController {



    private UserDetailsServiceImpl userDetailsService;


    @PostMapping("/add")
    public ResponseEntity<UserInfo> saveUser(@RequestBody UserInfo user){


        UserInfo userInfo =   userDetailsService.saveUser(user);


        return Optional.of(userInfo).map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
