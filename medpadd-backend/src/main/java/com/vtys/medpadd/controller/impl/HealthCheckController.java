package com.vtys.medpadd.controller.impl;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
public class HealthCheckController {
    @GetMapping("/health-check")
    public String getMethodName() {
        return "Server is working";
    }
    
}
