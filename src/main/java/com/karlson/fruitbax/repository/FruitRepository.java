package com.karlson.fruitbax.repository;

import com.karlson.fruitbax.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Optional<Fruit> findByNameIgnoreCase(String name);
}
