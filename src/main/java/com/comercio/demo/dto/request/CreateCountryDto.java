package com.comercio.demo.dto.request;

import com.comercio.demo.enums.CountryCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCountryDto {
    private Long idCountry;
    @NotBlank(message = "El nombre debe tener contenido")
    private String name;

    @NotNull(message = "El codigo de país no puede estar vació")
    private CountryCode countryCode;
}
