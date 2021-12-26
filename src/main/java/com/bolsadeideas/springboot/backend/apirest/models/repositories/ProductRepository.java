package com.bolsadeideas.springboot.backend.apirest.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Bill;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
