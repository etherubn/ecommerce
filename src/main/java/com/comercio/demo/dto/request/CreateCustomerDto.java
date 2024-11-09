package com.comercio.demo.dto.request;

import com.comercio.demo.validations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateCustomerDto {
    private Long idCustomer;
    @NotBlank(message = "El nombre no puede estar vació")
    @Size(max = 30,message = "El nombre debe tener como máximo 30 caracteres")
    private String name;

    @NotBlank(message = "El apellido no puede estar vació")
    @Size(max = 30,message = "El nombre debe tener como máximo 30 caracteres")
    private String lastName;

    @ValidEmail
    @NotBlank(message = "El correo no puede estar vació")
    private String email;

    @NotBlank(message = "El nombre de usuario no puede estar vació")
    private String username;

    @NotBlank(message = "El password de usuario no puede estar vació")
    @Size(max = 15,message = "El password debe tener como máximo 15 caracteres")
    private String password;

    @NotBlank(message = "Debe tener dni con 8 valores")
    @Size(min = 8,max = 8)
    private String dni;

    @NotNull(message = "Debe colocar el codigo de pais")
    @Positive(message = "El codigo de pais debe ser positivo")
    private Long idCountry;

    @JsonProperty("roles")
    @NotNull
    private Set<CreateRoleDto> rolesCustomer;
}
