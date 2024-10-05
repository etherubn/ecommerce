package com.comercio.demo.entity;


import com.comercio.demo.entity.idClass.OrderProductKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "order_product")
public class OrderProduct {

    @EmbeddedId
    private OrderProductKey orderProductKey;

    @NotNull
    @Positive
    private Integer quantity;


    @NotNull
    @Positive
    private BigDecimal subtotal;

    public OrderProduct() {
        this.orderProductKey = new OrderProductKey();
    }

    public OrderProduct(Integer quantity, Ordered ordered, Product product) {
        this.quantity = quantity;
        this.ordered = ordered;
        this.product = product;
        this.orderProductKey =new OrderProductKey(ordered.getId(), product.getId());
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
        this.orderProductKey.setOrderId(ordered.getId());
    }

    public void setProduct(Product product) {
        this.product = product;
        this.orderProductKey.setProductId(product.getId());
    }

    @ManyToOne
    @JoinColumn(name = "order_id")
    @MapsId(value = "orderId")
    private Ordered ordered;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @MapsId(value = "productId")
    private Product product;

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderProductKey=" + orderProductKey +
                ", quantity=" + quantity +
                ", ordered=" + ordered +
                ", product=" + product +
                '}';
    }
}
