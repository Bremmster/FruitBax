package com.karlson.fruitbax.service;

import com.karlson.fruitbax.entity.Fruit;
import com.karlson.fruitbax.model.FruitDTO;
import com.karlson.fruitbax.repository.FruitRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FruitService {

    FruitRepository fruitRepository;

    public FruitService(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public List<FruitDTO> findAll() {
        return fruitRepository.findAll().stream().map(fruit -> new FruitDTO(fruit.getName()))
                .toList();
    }

    public FruitDTO findByName(String fruitName) {

        return fruitRepository.findByNameIgnoreCase(fruitName);
    }

    public FruitDTO addFruit(FruitDTO newFruit) throws IllegalArgumentException {

        return new FruitDTO(fruitRepository.save(new Fruit(newFruit)).getName());
    }
}
