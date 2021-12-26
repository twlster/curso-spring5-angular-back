package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Bill;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Client;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Product;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;

public interface ClientsService{

	public List<Client> findAll();

	public List<Region> findAllRegions();

	public Page<Client> findAll(PageRequest pageRequest);
	
	public Client findById(Long id);
	
	public Client save(Client client);
	
	public void delete(Long id);

	public List<Bill> findAllBills();
	
	public Bill findBillById(Long id);
	
	public Bill saveBill(Bill bill);
	
	public void deleteBill(Long id);
	
	public List<Product> findAllProduct();
}
