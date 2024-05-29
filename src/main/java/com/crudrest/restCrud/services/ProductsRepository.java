package com.crudrest.restCrud.services;

import com.crudrest.restCrud.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {
}
