package com.comercio.demo.dto.request;

import com.comercio.demo.entity.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDto {
    @NotNull(message = "El nobmre del producto debe tener contenido")
    private String name;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @NotNull(message = "El stock debe tener contenido")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Integer stock;

    @NotNull(message = "El precio debe tener contenido")
    @Positive(message = "El precio debe ser positivo")
    @Digits(integer = 5,fraction = 2,message = "Debe ser número")
    private BigDecimal price;

    @NotNull(message = "La descripcion debe tener contenido")
    @NotBlank(message = "La descripción no puede estra vacía")
    @Size(max = 40,message = "La descrición debe tener máximo 40 caracteres")
    private String description;

    @NotNull(message = "Debe ingresar la categoría")
    @Positive(message = "La categoría debe ser positiva")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long category;
}
