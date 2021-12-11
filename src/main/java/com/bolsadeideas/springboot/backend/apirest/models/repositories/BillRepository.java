package com.bolsadeideas.springboot.backend.apirest.models.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{

}
