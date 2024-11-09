package com.comercio.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderedProductDto {

    private Long idOrderedProduct;

    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    @NotNull(message = "Debe tener una cantidad")
    @PositiveOrZero(message = "La cantidad debe ser positiva")
    private Integer quantity;

    @NotNull(message = "Debe tener producto")
    @Positive(message = "Debe ser positivo")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long product;
}
