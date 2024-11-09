package com.comercio.demo.dto.request;

import com.comercio.demo.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCategoryDto {

    private Long idCategory;
    @NotNull(message = "El tipo de categoría no puede estar vació")
    private CategoryType tipo;
}

