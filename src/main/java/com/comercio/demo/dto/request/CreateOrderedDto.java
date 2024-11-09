package com.comercio.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateOrderedDto {

    private Long idOrdered;
    @JsonProperty("customer")
    @NotNull(message = "El id no debe ser nulo")
    @Positive(message = "Debe tener id mayor a 0")
    private Long idCustomer;

    @JsonProperty("products")
    @NotNull
    private Set<CreateOrderedProductDto> orderedProducts;

}
