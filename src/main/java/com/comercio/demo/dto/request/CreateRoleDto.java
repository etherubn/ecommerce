package com.comercio.demo.dto.request;


import com.comercio.demo.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateRoleDto {
    private Long idRole;
    @NotNull
    private RoleEnum role;
    @JsonProperty("permissions")
    @NotNull
    private Set<CreatePermissionDto> permissions;
}
