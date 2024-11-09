package com.comercio.demo.dto.response;


import com.comercio.demo.entity.Review;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;



@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseProductDto implements Serializable {
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private String description;
    @JsonProperty("category")
    private ResponseCategoryDto categoryProduct;

}
