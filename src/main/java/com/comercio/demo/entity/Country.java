package com.comercio.demo.entity;

import com.comercio.demo.enums.CountryCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "country")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_country")
    @EqualsAndHashCode.Include
    private Long idCountry;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CountryCode countryCode;


    @Override
    public String toString() {
        return "Country{" +
                "id=" + idCountry +
                ", name='" + name + '\'' +
                ", countryCode=" + countryCode +
                '}';
    }
}
