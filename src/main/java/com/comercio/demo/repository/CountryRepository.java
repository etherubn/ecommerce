package com.comercio.demo.repository;

import com.comercio.demo.entity.Country;
import com.comercio.demo.enums.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    public boolean existsByCountryCode(CountryCode countryCode);

    @Query("select c from Country c left join fetch c.customers where c.id=?1")
    Optional<Country> findCountry(Long id);
}
