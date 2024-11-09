package com.comercio.demo.repository;

import com.comercio.demo.entity.Country;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends RepoGeneric<Country,Long> {

//    public boolean existsByCountryCode(CountryCode countryCode);
//
//    @Query("select c from Country c left join fetch c.customers where c.id=?1")
//    Optional<Country> findCountry(Long id);
}
