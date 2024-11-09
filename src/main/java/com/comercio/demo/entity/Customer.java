package com.comercio.demo.entity;

import com.comercio.demo.validations.ValidEmail;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    @EqualsAndHashCode.Include
    private Long idCustomer;

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
    @Size(max = 80)
    private String password;


    //Todo
    //Debe colocarse de forma automatica al momento de
    //registrarse el usuario

    @Column(updatable = false,nullable = false)
    private LocalDateTime creationDate;

    @PrePersist
    public void setCreationTime(){
        this.creationDate = LocalDateTime.now();
    }

    //Todo
    //Debeiniciar en 0
    //luego de realizar la compra aumentar
    @PositiveOrZero
    @NotNull
    private Integer purchaseAmount=0;

    private boolean isEnabled;
    private boolean accountNoExpired;
    private boolean accountNoLocked;
    private boolean credentialNoExpired;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_country")
    private Country country;

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE})
    @JoinTable(name = "customer_role"
        ,joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "Customer{" +
                "idCustomer=" + idCustomer +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", creationDate=" + creationDate +
                ", purchaseAmount=" + purchaseAmount +
                ", country=" + country +
                ", roles=" + roles +
                '}';
    }
}
