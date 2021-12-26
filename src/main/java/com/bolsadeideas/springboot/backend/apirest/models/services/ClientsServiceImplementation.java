package com.bolsadeideas.springboot.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Bill;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Client;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Product;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Region;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.BillRepository;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.ClientsRepository;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.ProductRepository;
import com.bolsadeideas.springboot.backend.apirest.models.repositories.RegionRepository;

@Service
@Transactional
public class ClientsServiceImplementation implements ClientsService{

	@Autowired
	private ClientsRepository repository;
	
	@Autowired
	private BillRepository billRepository;
	
	@Autowired
	private RegionRepository regionRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Client> findAll() {
		return (List<Client>) repository.findAll();
	}
	
	@Override
	public Page<Client> findAll(PageRequest pageRequest) {
		return (Page<Client>) repository.findAll(pageRequest);
	}

	@Override
	public Client findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	@Override
	public Client save(Client client) {
		return repository.save(client);
	}

	@Override
	public void delete(Long id) {
		repository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Region> findAllRegions() {
		return regionRepository.findAll();
	}

	@Override
	public List<Bill> findAllBills() {
		return (List<Bill>) billRepository.findAll();
	}

	@Override
	public Bill findBillById(Long id) {
		return billRepository.findById(id).orElse(null);
	}

	@Override
	public Bill saveBill(Bill bill) {
		return billRepository.save(bill);
	}

	@Override
	public void deleteBill(Long id) {
		billRepository.deleteById(id);
	}

	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

}
