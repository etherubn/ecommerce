package com.comercio.demo.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseReviewDto implements Serializable {
    private Long id;
    private LocalDateTime creationDate;
    private Integer rating;
    private String comment;
    @JsonProperty("customer")
    private ResponseCustomerDto customerReview;
    @JsonProperty("product")
    private ResponseProductDto productReview;

}
