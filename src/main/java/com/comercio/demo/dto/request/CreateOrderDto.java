package com.comercio.demo.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDto {

    @JsonProperty("customer")
    @NotNull(message = "El id no debe ser nulo")
    @Positive(message = "Debe tener id mayor a 0")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long idCustomer;

    @JsonProperty("products")
    @NotNull
    private Set<CreateOrderProductDto> orderProducts;



}
