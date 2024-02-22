package com.karlson.fruitbax.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingController {

    @GetMapping("")
    public ResponseEntity<String> pingPong() {
        return ResponseEntity.ok("Never retire, stare and admire, my drip don’t expire");
    }
}
