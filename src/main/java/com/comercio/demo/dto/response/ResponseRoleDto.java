package com.comercio.demo.dto.response;

import com.comercio.demo.enums.RoleEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseRoleDto implements Serializable {
    private Long idRole;
    private RoleEnum role;
    @JsonProperty(access=JsonProperty.Access.WRITE_ONLY)
    private Set<ResponsePermissionDto> permissions;
}
