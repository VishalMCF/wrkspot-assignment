package com.example.asswrkspt.web.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/healthcheck")
public class HealthCheckController {

    @GetMapping
    public ResponseEntity<Map<String, Boolean>> checkHealth() {
        return ResponseEntity.ok(Map.of("success", true));
    }
}
