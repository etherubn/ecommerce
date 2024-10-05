package com.comercio.demo.dto.request;

import com.comercio.demo.enums.CategoryType;
import com.comercio.demo.validations.ExistedOption;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCategoryDto {


    @NotNull(message = "El tipo de categoría no puede estar vació")
    private CategoryType tipo;
}

