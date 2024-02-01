package com.karlson.fruitbax.repository;

import com.karlson.fruitbax.entity.Fruit;
import com.karlson.fruitbax.model.FruitDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    FruitDTO findByNameIgnoreCase(String name);
}
