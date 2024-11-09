package com.comercio.demo.entity;


import com.comercio.demo.entity.idClass.OrderProductKey;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "order_product")
public class OrderedProduct {

    @EmbeddedId
    @EqualsAndHashCode.Include
    private OrderProductKey idOrderedProduct;

    @NotNull
    @Positive
    private Integer quantity;


    @NotNull
    @Positive
    private BigDecimal subtotal;

    public OrderedProduct() {
        this.idOrderedProduct = new OrderProductKey();
    }

    public OrderedProduct(Integer quantity, Ordered ordered, Product product) {
        this.quantity = quantity;
        this.ordered = ordered;
        this.product = product;
        this.idOrderedProduct =new OrderProductKey(ordered.getIdOrdered(), product.getIdProduct());
    }

    public void setOrdered(Ordered ordered) {
        this.ordered = ordered;
        this.idOrderedProduct.setOrderId(ordered.getIdOrdered());
    }

    public void setProduct(Product product) {
        this.product = product;
        this.idOrderedProduct.setProductId(product.getIdProduct());
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
        return "OrderedProduct{" +
                "orderProductKey=" + idOrderedProduct +
                ", quantity=" + quantity +
                ", ordered=" + ordered +
                ", product=" + product +
                '}';
    }
}
