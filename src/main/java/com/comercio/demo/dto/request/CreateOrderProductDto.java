package com.comercio.demo.dto.request;

import com.comercio.demo.entity.Ordered;
import com.comercio.demo.entity.Product;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderProductDto {

    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    @NotNull(message = "Debe tener una cantidad")
    @PositiveOrZero(message = "La cantidad debe ser positiva")
    private Integer quantity;

    @NotNull(message = "Debe tener producto")
    @Positive(message = "Debe ser positivo")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long product;
}
