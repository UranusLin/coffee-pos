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

    @GetMapping("api/v1/health")
    public String healthCheckV1() {
        return "Application v1 is running";
    }

    @GetMapping("api/v2/health")
    public String healthCheckV2() {
        return "Application v2 is running";
    }
}
