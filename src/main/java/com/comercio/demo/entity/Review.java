package com.comercio.demo.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_review")
    @EqualsAndHashCode.Include
    private Long idReview;

    @NotNull
    @Column(updatable = false)
    private LocalDateTime creationDate;

    @Max(value = 10)
    @Min(value = 0)
    private Integer rating;

    @NotBlank
    @Size(max = 50)
    private String comment;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    @JoinColumn(name = "id_product")
    private Product product;

    @PrePersist
    public void setCreationTime(){
        this.creationDate= LocalDateTime.now();
    }
}
