package com.comercio.demo.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseSelling {
    private String product;
    private BigDecimal price;
    private Integer quantity;
    private BigDecimal subtotal;
}
