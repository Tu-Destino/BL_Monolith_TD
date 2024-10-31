package com.TD.BL_Monolith_TD.api.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Exa {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, world!";
    }
}
