package com.comercio.demo.entity;

import com.comercio.demo.validations.ValidEmail;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String name;

    @NotBlank
    @Size(max = 30)
    private String lastName;

    @NotBlank
    @Column(unique = true)
    private String dni;

    @ValidEmail

    @NotBlank
    @Column(unique = true)
    private String email;


    @NotBlank
    @Column(unique = true)
    private String username;

    @NotBlank
    @Size(max = 15)
    private String password;


    //Todo
    //Debe colocarse de forma automatica al momento de
    //registrarse el usuario

    @Column(updatable = false,nullable = false)
    private LocalDateTime creationDate;

    @PrePersist
    public void setCreationTime(){
        this.creationDate = LocalDateTime.now();
        System.out.println("Poneido hora");
    }

    //Todo
    //Debeiniciar en 0
    //luego de realizar la compra aumentar
    @PositiveOrZero
    @NotNull
    private Integer purchaseAmount=0;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Review> reviews= new HashSet<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Ordered> ordereds = new HashSet<>();

    public Customer(String name, String lastName, String email, String username, String password, Country country) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.username = username;
        this.password = password;
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer customer)) return false;
        return Objects.equals(dni, customer.dni);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(dni);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
//                ", purchaseAmount=" + purchaseAmount +
//                ", reviews=" + reviews +
//                ", ordereds=" + ordereds +
                '}';
    }
}
