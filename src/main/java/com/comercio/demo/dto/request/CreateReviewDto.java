package com.comercio.demo.dto.request;

import com.comercio.demo.dto.response.ResponseCustomerDto;
import com.comercio.demo.entity.Customer;
import com.comercio.demo.entity.Product;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateReviewDto {

    @Min(value = 0,message = "El valor mínimo debe ser 0")
    @Max(value = 10,message = "El máximo valor es 10")
    @Digits(integer = 2,fraction = 0,message = "Debe ser número")
    private Integer rating;

    @NotBlank(message = "El comentario debe tener contenido")
    @Size(max = 50,message = "El comentario debe tener como máximo 50 caracteres")
    private String comment;

    @NotNull(message = "El id del usuario debe tener contenido")
    @Positive(message = "El id del costumer debe ser positivo")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long idCustomer;

    @NotNull(message = "El id del producto debe tener contenido")
    @Positive(message = "El id del producto debe ser positivo")
    @Digits(integer = 5,fraction = 0,message = "Debe ser número")
    private Long idProduct;
}
