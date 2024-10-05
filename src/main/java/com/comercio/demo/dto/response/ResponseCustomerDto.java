package com.comercio.demo.dto.response;



import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseCustomerDto implements Serializable {
    private Long id;
    private String name;
    private String lastName;
    private String email;
    private String username;
    private String dni;
    private LocalDateTime creationDate;
    private Integer purchaseAmount;
    @JsonProperty("pais")
    private ResponseCountryDto responseCountryDto;
}
