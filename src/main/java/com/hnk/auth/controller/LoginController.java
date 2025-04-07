package com.hnk.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/login")
    public ResponseEntity<String> login(){
        return ResponseEntity.ok().body("Login");
    }

    @GetMapping("/hello")
    public String hello(){
        return "private";
    }

}
