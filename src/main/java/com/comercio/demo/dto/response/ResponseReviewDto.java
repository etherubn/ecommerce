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
public class ResponseReviewDto implements Serializable {
    private Long id;
    private LocalDateTime creationDate;
    private Integer rating;
    private String comment;
    @JsonProperty("customer")
    private ResponseCustomerDto customer;
    @JsonProperty("product")
    private ResponseProductDto product;

}
