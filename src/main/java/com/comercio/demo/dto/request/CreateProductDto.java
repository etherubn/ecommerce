package com.comercio.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateProductDto {


    private Long idProduct;

    @NotNull(message = "El nobmre del producto debe tener contenido")
    private String name;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @NotNull(message = "El stock debe tener contenido")
    private Integer stock;

    @NotNull(message = "El precio debe tener contenido")
    @Positive(message = "El precio debe ser positivo")
    private BigDecimal price;

    @NotBlank(message = "La descripción no puede estra vacía")
    @Size(max = 40,message = "La descrición debe tener máximo 40 caracteres")
    private String description;

    @Positive(message = "La categoría debe ser positiva")
    @NotNull
    private Long idCategory;
}
