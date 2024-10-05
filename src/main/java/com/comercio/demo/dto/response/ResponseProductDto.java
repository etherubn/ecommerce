package com.comercio.demo.dto.response;


import com.comercio.demo.entity.Review;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseProductDto implements Serializable {
    private Long id;
    private String name;
    private Integer stock;
    private BigDecimal price;
    private String description;
    @JsonProperty("category")
    private ResponseCategoryDto responseCategoryDto;

}
