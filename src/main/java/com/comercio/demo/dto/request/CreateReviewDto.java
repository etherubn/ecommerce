package com.comercio.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateReviewDto {

    private Long idReview;

    @Min(value = 0,message = "El valor mínimo debe ser 0")
    @Max(value = 10,message = "El máximo valor es 10")
    private Integer rating;

    @NotBlank(message = "El comentario debe tener contenido")
    @Size(max = 50,message = "El comentario debe tener como máximo 50 caracteres")
    private String comment;

    @NotNull(message = "El id del usuario debe tener contenido")
    @Positive(message = "El id del costumer debe ser positivo")
    private Long idCustomer;

    @NotNull(message = "El id del producto debe tener contenido")
    @Positive(message = "El id del producto debe ser positivo")
    private Long idProduct;
}
