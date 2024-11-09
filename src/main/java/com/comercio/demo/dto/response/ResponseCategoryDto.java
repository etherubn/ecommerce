package com.comercio.demo.dto.response;


import com.comercio.demo.enums.CategoryType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseCategoryDto implements Serializable {
    private Long id;
    private CategoryType tipo;
}
