package com.comercio.demo.dto.response;



import com.comercio.demo.enums.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseCountryDto implements Serializable {
    private Long id;
    private String name;
    private CountryCode countryCode;

}
