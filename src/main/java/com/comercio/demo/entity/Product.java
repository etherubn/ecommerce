package com.comercio.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_product")
    private Long idProduct;

    @NotBlank
    private String name;

    @PositiveOrZero
    @NotNull
    private Integer stock;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

    @NotBlank
    @Size(max = 40)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id",nullable = false)
    private Category category;


    @Override
    public String toString() {
        return "Product{" +
                "id=" + idProduct +
                ", name='" + name + '\'' +
                ", stock=" + stock +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", category=" + category;
    }
}
