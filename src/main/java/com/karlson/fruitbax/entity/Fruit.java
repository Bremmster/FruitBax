package com.karlson.fruitbax.entity;

import com.karlson.fruitbax.model.FruitDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "fruit")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @JdbcTypeCode(SqlTypes.BIGINT)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public Fruit(FruitDTO fruitDTO) {
        this.name = formatName(fruitDTO.name());
    }

    public Fruit() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = formatName(name);
    }

    public String formatName(String name) {
        // make first char uppercase and the rest lowercase
        String formattedName = name.toLowerCase();
        return formattedName.substring(0, 1).toUpperCase() + formattedName.substring(1);
    }
}
