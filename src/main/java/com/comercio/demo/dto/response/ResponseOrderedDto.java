package com.comercio.demo.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseOrderedDto implements Serializable {
    private Long id;
    private LocalDateTime orderedDate;
    private String username;
    private String email;
    @JsonProperty("products")
    private List<ResponseSelling> responseSellings;
    private BigDecimal total;
}
