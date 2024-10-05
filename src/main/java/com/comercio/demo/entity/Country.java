package com.comercio.demo.entity;

import com.comercio.demo.enums.CountryCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long id;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    private CountryCode countryCode;


    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Customer> customers = new HashSet<>();

    public Country(String name, CountryCode countryCode) {
        this.name = name;
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Country country)) return false;
        return countryCode == country.countryCode;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(countryCode);
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", countryCode=" + countryCode +
                ", customers=" + customers +
                '}';
    }
}
