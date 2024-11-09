package com.comercio.demo.dto.response;

import com.comercio.demo.enums.CountryCode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCountryDto implements Serializable {
    private Long id;
    private String name;
    private CountryCode countryCode;

}
