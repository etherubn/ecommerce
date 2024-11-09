package com.comercio.demo.dto.response;



import com.comercio.demo.dto.request.CreateRoleDto;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCustomerDto implements Serializable {
    private Long idCustomer;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String dni;
    private LocalDateTime creationDate;
    private Integer purchaseAmount;
    @JsonProperty("pais")
    private ResponseCountryDto countryCustomer;
    @JsonProperty("roles")
    private Set<ResponseRoleDto> rolesCustomer;
}
