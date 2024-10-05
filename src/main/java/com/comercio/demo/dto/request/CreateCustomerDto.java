package com.comercio.demo.dto.request;

import com.comercio.demo.validations.ValidEmail;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCustomerDto {

    @NotBlank(message = "El nombre no puede estar vació")
    @NotNull(message = "El nombre debe tener contenido")
    @Size(max = 30,message = "El nombre debe tener como máximo 30 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vació")
    @NotNull(message = "El apellido debe tener contenido")
    @Size(max = 30,message = "El nombre debe tener como máximo 30 caracteres")
    private String lastName;

    @ValidEmail
    @NotNull(message = "El correo debe tener contenido")
    @NotBlank(message = "El correo no puede estar vació")
    private String email;

    @NotNull(message = "El nombre de usuario debe tener contenido")
    @NotBlank(message = "El nombre de usuario no puede estar vació")
    private String username;

    @NotBlank(message = "El password de usuario no puede estar vació")
    @NotNull(message = "El password de usuario debe tener contenido")
    @Size(max = 15,message = "El password debe tener como máximo 15 caracteres")
    private String password;

    @NotBlank(message = "Debe tener dni")
    @Size(min = 8,max = 8)
    private String dni;

    @NotNull(message = "Debe colocar el codigo de pais")
    @Positive(message = "El codigo de pais debe ser positivo")
    private Long countryId;
}
