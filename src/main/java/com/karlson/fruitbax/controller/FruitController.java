package com.karlson.fruitbax.controller;

import com.karlson.fruitbax.model.FruitDTO;
import com.karlson.fruitbax.model.UpdateFruitDTO;
import com.karlson.fruitbax.service.FruitService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fruitbax/fruit")
public class FruitController {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @GetMapping("")
    public ResponseEntity<List<FruitDTO>> getAll() {
        List<FruitDTO> fruits = fruitService.findAll();

        return fruits.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(fruits);
    }

    @GetMapping("/{fruitName}")
    public ResponseEntity<FruitDTO> getOneFruitByName(@PathVariable String fruitName) {
        FruitDTO fruit = fruitService.findByName(fruitName);

        return fruit == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(fruit);
    }

    @PostMapping("")
    public ResponseEntity<FruitDTO> createFruit(@RequestBody FruitDTO newFruit) {
        FruitDTO fruit = fruitService.addFruit(newFruit);

        return fruit == null ? ResponseEntity.badRequest().build() : ResponseEntity.ok(fruit);
    }

    @PutMapping("")
    public ResponseEntity<?> updateFruit(@RequestBody UpdateFruitDTO updateFruit) {

        FruitDTO fruit = fruitService.updateFruit(updateFruit);

        return fruit == null ? ResponseEntity.badRequest().body("The fruit you want to replace does not exist or the new fruit already exists") : ResponseEntity.ok(fruit);
    }
}
