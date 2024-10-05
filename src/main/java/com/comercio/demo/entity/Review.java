package com.comercio.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @Max(value = 10)
    @Min(value = 0)
    private Integer rating;

    @NotBlank
    @Size(max = 50)
    private String comment;

    @ManyToOne
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public Review(Integer rating, String comment, Customer customer, Product product) {
        this.rating = rating;
        this.comment = comment;
        this.customer = customer;
        this.product = product;
    }

    @PrePersist
    public void setCreationTime(){
        this.creationDate= LocalDateTime.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return Objects.equals(customer, review.customer) && Objects.equals(product, review.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, product);
    }
}
