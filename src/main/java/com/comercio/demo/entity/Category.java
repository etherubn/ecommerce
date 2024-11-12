package com.comercio.demo.entity;

import com.comercio.demo.enums.CategoryType;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_category")
    private Long idCategory;

    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(nullable = false)
    private CategoryType tipo;

    public Category(CategoryType tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + idCategory +
                ", tipo=" + tipo +
                '}';
    }
}
