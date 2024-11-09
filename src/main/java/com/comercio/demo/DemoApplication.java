package com.comercio.demo;


import lombok.Data;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;


@SpringBootApplication
public class DemoApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Data
	class Product {
		private String name;
		private Double price;

		public Product(String name, Double price) {
			this.name = name;
			this.price = price;
		}
	}

	@Override
	public void run(String... args) throws Exception {
		List<Product> products = new ArrayList<>();
		products.add(new Product("product1",9.99));
		products.add(new Product("product2",19.99));
		products.add(new Product("product3",29.99));
		products.add(new Product("product4",39.99));

		List<Product> productList = products.stream()
				.filter(product -> product.getPrice()>10)
				.map(product -> {
					Product product1 = new Product(product.getName(), product.getPrice()*1.21);
					return product1;
				})
				.toList();

		productList.stream().forEach(System.out::println);
	}
}
