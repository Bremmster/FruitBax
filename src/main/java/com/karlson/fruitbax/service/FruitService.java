package com.karlson.fruitbax.service;

import com.karlson.fruitbax.entity.Fruit;
import com.karlson.fruitbax.model.FruitDTO;
import com.karlson.fruitbax.model.UpdateFruitDTO;
import com.karlson.fruitbax.repository.FruitRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        return fruitRepository.findByNameIgnoreCase(fruitName).map(value -> new FruitDTO(value.getName())).orElse(null);
    }

    public FruitDTO addFruit(FruitDTO newFruit) throws IllegalArgumentException {

        return new FruitDTO(fruitRepository.save(new Fruit(newFruit)).getName());
    }

    @Transactional
    public FruitDTO updateFruit(UpdateFruitDTO updateFruit) {

        /* check if fruit exists both old and new
            if !old return null
            if new return null
            else update and return fruit
         */
        Optional<Fruit> target = fruitRepository.findByNameIgnoreCase(updateFruit.name());
        if (target.isEmpty() || fruitRepository.findByNameIgnoreCase(updateFruit.newName()).isPresent())
            return null;
        target.get().setName(updateFruit.newName());

        return fruitRepository.findByNameIgnoreCase(updateFruit.newName()).map(value -> new FruitDTO(value.getName())).orElse(null);

    }
}
