package com.comercio.demo;

import com.comercio.demo.entity.Category;
import com.comercio.demo.entity.Country;
import com.comercio.demo.repository.CategoryRepository;
import com.comercio.demo.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
public class DemoApplication{
	private CountryRepository countryRepository;

	public DemoApplication(CountryRepository countryRepository) {
		this.countryRepository = countryRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}






}
