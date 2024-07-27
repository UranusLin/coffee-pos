package com.coffee.pos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthCheckController {
    
    /*
     * GetMapping
     * PostMapping
     * PutMapping
     * PatchMapping
     * DeleteMapping
     */

    @GetMapping("/health")
    public String healthCheck() {
        return "Application is running";
    }
}
