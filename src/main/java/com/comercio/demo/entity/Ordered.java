package com.comercio.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ordered_id")
    private Long id;

    @NotNull
    @Column(updatable = false,nullable = false)
    private LocalDateTime orderDate;

    @PrePersist
    public void createDateOrder(){
        orderDate= LocalDateTime.now();
    }

    @NotNull
    @PositiveOrZero
    private BigDecimal total;

    private boolean isCanceled = false;
    private boolean isDeleted = false;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "ordered",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    public Ordered(Customer customer, List<OrderProduct> orderProducts) {
        this.customer = customer;
        this.orderProducts = orderProducts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ordered ordered)) return false;
        return Objects.equals(orderDate, ordered.orderDate) && Objects.equals(customer, ordered.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderDate, customer);
    }

    @Override
    public String toString() {
        return "Ordered{" +
                "id=" + id +
                ", orderDate=" + orderDate +
                ", total=" + total;
    }
}
