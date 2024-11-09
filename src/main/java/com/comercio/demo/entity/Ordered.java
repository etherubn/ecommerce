package com.comercio.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "ordered")
public class Ordered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordered")
    @EqualsAndHashCode.Include
    private Long idOrdered;

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

    @OneToMany(mappedBy = "ordered",cascade = CascadeType.MERGE)
    private List<OrderedProduct> orderedProducts = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Override
    public String toString() {
        return "Ordered{" +
                "id=" + idOrdered +
                ", orderDate=" + orderDate +
                ", total=" + total;
    }
}
