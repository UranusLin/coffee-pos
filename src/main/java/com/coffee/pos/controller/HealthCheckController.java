package com.coffee.pos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.coffee.pos.dto.CommonResponse;


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
    public ResponseEntity<CommonResponse> healthCheckV2() {
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode("00");
        commonResponse.setMsg("success");
        return new ResponseEntity<>(commonResponse, HttpStatus.OK);
    }
}
