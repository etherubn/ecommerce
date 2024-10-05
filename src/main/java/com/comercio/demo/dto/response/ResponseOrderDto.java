package com.comercio.demo.dto.response;


import com.comercio.demo.entity.Customer;
import com.comercio.demo.entity.OrderProduct;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ResponseOrderDto implements Serializable {
    private Long id;
    private LocalDateTime orderedDate;
    private String username;
    private String email;
    @JsonProperty("products")
    private List<ResponseSelling> responseSellings;
    private BigDecimal total;
}
