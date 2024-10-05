package com.comercio.demo.dto.request;

import com.comercio.demo.enums.CountryCode;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCountryDto {
    @NotBlank(message = "El nombre debe tener contenido")
    private String name;

    @NotNull(message = "El codigo de país no puede estar vació")
    private CountryCode countryCode;
}
