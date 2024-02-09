package com.karlson.fruitbax.repository;

import com.karlson.fruitbax.entity.Fruit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FruitRepository extends JpaRepository<Fruit, Long> {
    Optional<Fruit> findByNameIgnoreCase(String name);

    @Modifying
    @Query("update Fruit f set f.name = :newName where f.name = :oldName")
    void updateFruitByName(@Param("oldName") String oldName, @Param("newName") String newName);
}
